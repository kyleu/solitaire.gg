package controllers

import java.util.UUID

import _root_.services.database.Database
import _root_.services.user.AuthenticationEnvironment
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.audit.RequestLog
import models.database.queries.RequestLogQueries
import models.user.{ Role, User, WithRole }
import nl.grons.metrics.scala.Timer
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{RequestHeader, AnyContent, Result}
import utils.Logging
import utils.metrics.Instrumented

import scala.concurrent.Future

object BaseController extends Instrumented {
  val timers = collection.mutable.HashMap.empty[String, Timer]
}

abstract class BaseController extends Silhouette[User, CookieAuthenticator] with Instrumented with Logging {
  override protected def env = AuthenticationEnvironment

  object AdminAction extends SecuredActionBuilder(Some(WithRole(Role.Admin)))

  def withSession(block: (SecuredRequest[AnyContent]) => Future[Result]) = UserAwareAction.async { implicit request =>
    val startTime = System.currentTimeMillis
    val response = request.identity match {
      case Some(user) =>
        val secured = SecuredRequest(user, request.authenticator.getOrElse(throw new IllegalStateException()), request)
        block(secured).map { r =>
          logRequest(secured, secured.identity.id, secured.authenticator.loginInfo, System.currentTimeMillis - startTime, r.header.status)
          r
        }
      case None =>
        val user = User(
          id = UUID.randomUUID(),
          username = None,
          avatar = "guest",
          profiles = Nil,
          created = new LocalDateTime()
        )

        for {
          user <- env.identityService.save(user)
          authenticator <- env.authenticatorService.create(LoginInfo("anonymous", user.id.toString))
          value <- env.authenticatorService.init(authenticator)
          authedResponse <- env.authenticatorService.embed(value, block(SecuredRequest(user, authenticator, request)))
        } yield {
          env.eventBus.publish(SignUpEvent(user, request, request2lang))
          env.eventBus.publish(LoginEvent(user, request, request2lang))
          logRequest(request, user.id, authenticator.loginInfo, System.currentTimeMillis - startTime, authedResponse.header.status)
          authedResponse
        }
    }
    response
  }

  private[this] def logRequest(request: RequestHeader, userId: UUID, loginInfo: LoginInfo, duration: Long, status: Int) = {
    val log = RequestLog(request, userId, loginInfo, duration, status)
    Database.execute(RequestLogQueries.CreateRequest(log))
  }
}

package controllers

import java.util.UUID

import models.database.queries.RequestLogQueries
import play.api.i18n.I18nSupport
import services.database.Database
import services.user.AuthenticationEnvironment
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.audit.RequestLog
import models.user.{ Role, User }
import nl.grons.metrics.scala.Timer
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ AnyContent, RequestHeader, Result }
import utils.Logging
import utils.metrics.Instrumented

import scala.concurrent.Future

object BaseController extends Instrumented {
  val timers = collection.mutable.HashMap.empty[String, Timer]
}


abstract class BaseController extends Silhouette[User, CookieAuthenticator] with I18nSupport with Instrumented with Logging {
  override protected def env = AuthenticationEnvironment

  def withAdminSession(block: (SecuredRequest[AnyContent]) => Future[Result]) = SecuredAction.async { implicit request =>
    val startTime = System.currentTimeMillis
    if (request.identity.roles.contains(Role.Admin)) {
      block(request).map { r =>
        val duration = (System.currentTimeMillis - startTime).toInt
        logRequest(request, request.identity.id, request.authenticator.loginInfo, duration, r.header.status)
        r
      }
    } else {
      Future.successful(NotFound("404 Not Found"))
    }
  }

  def withSession(block: (SecuredRequest[AnyContent]) => Future[Result]) = UserAwareAction.async { implicit request =>
    val startTime = System.currentTimeMillis
    val response = request.identity match {
      case Some(user) =>
        val secured = SecuredRequest(user, request.authenticator.getOrElse(throw new IllegalStateException()), request)
        block(secured).map { r =>
          val duration = (System.currentTimeMillis - startTime).toInt
          logRequest(secured, secured.identity.id, secured.authenticator.loginInfo, duration, r.header.status)
          r
        }
      case None =>
        val user = User(
          id = UUID.randomUUID(),
          username = None,
          avatar = "guest",
          color = "greyblue",
          profiles = Nil,
          created = new LocalDateTime()
        )

        for {
          user <- env.identityService.save(user)
          authenticator <- env.authenticatorService.create(LoginInfo("anonymous", user.id.toString))
          value <- env.authenticatorService.init(authenticator)
          result <- block(SecuredRequest(user, authenticator, request))
          authedResponse <- env.authenticatorService.embed(value, result)
        } yield {
          env.eventBus.publish(SignUpEvent(user, request, request2Messages))
          env.eventBus.publish(LoginEvent(user, request, request2Messages))
          val duration = (System.currentTimeMillis - startTime).toInt
          logRequest(request, user.id, authenticator.loginInfo, duration, authedResponse.header.status)
          authedResponse
        }
    }
    response
  }

  private[this] def logRequest(request: RequestHeader, userId: UUID, loginInfo: LoginInfo, duration: Int, status: Int) = {
    val log = RequestLog(request, userId, loginInfo, duration, status)
    Database.execute(RequestLogQueries.Insert(log)).map(i => log)
  }
}

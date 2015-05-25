package controllers

import java.util.UUID

import services.audit.RequestLogService
import services.user.AuthenticationEnvironment
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.audit.RequestLog
import models.user.{ Role, User }
import nl.grons.metrics.scala.Timer
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ RequestHeader, AnyContent, Result }
import utils.Logging
import utils.metrics.Instrumented

import scala.concurrent.Future

object BaseController extends Instrumented {
  val timers = collection.mutable.HashMap.empty[String, Timer]
}

abstract class BaseController extends Silhouette[User, CookieAuthenticator] with Instrumented with Logging {
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
          val duration = (System.currentTimeMillis - startTime).toInt
          logRequest(request, user.id, authenticator.loginInfo, duration, authedResponse.header.status)
          authedResponse
        }
    }
    response
  }

  private[this] def logRequest(request: RequestHeader, userId: UUID, loginInfo: LoginInfo, duration: Int, status: Int) = {
    val log = RequestLog(request, userId, loginInfo, duration, status)
    RequestLogService.save(log)
  }
}

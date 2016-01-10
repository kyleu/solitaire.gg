package controllers

import java.util.UUID

import models.history.RequestLog
import play.api.i18n.I18nSupport
import services.history.RequestHistoryService
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.user.{ Role, User, UserPreferences }
import nl.grons.metrics.scala.FutureMetrics
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ Request, AnyContent, RequestHeader, Result }
import utils.metrics.Instrumented
import utils.{ ApplicationContext, DateUtils, Logging }

import scala.concurrent.Future

abstract class BaseController() extends Silhouette[User, CookieAuthenticator] with I18nSupport with Instrumented with FutureMetrics with Logging {
  def ctx: ApplicationContext

  override protected def env = ctx.env
  override def messagesApi = ctx.messagesApi

  def req(action: String)(block: (Request[AnyContent]) => Future[Result]) = UserAwareAction.async { implicit request =>
    timing(action) {
      val startTime = System.currentTimeMillis
      val response = {
        block(request).map { r =>
          val duration = (System.currentTimeMillis - startTime).toInt
          logRequest(request, None, None, duration, r.header.status)
          r
        }
      }
      response
    }
  }

  def withSession(action: String)(block: (SecuredRequest[AnyContent]) => Future[Result]) = UserAwareAction.async { implicit request =>
    timing(action) {
      val startTime = System.currentTimeMillis
      val response = request.identity match {
        case Some(user) =>
          val secured = SecuredRequest(user, request.authenticator.getOrElse(throw new IllegalStateException()), request)
          block(secured).map { r =>
            val duration = (System.currentTimeMillis - startTime).toInt
            logRequest(secured, Some(secured.identity.id), Some(secured.authenticator.loginInfo), duration, r.header.status)
            r
          }
        case None =>
          val user = User(
            id = UUID.randomUUID(),
            username = None,
            preferences = UserPreferences(),
            profiles = Nil,
            created = DateUtils.now
          )

          for {
            user <- ctx.env.userService.save(user)
            authenticator <- ctx.env.authenticatorService.create(LoginInfo("anonymous", user.id.toString))
            value <- ctx.env.authenticatorService.init(authenticator)
            result <- block(SecuredRequest(user, authenticator, request))
            authedResponse <- ctx.env.authenticatorService.embed(value, result)
          } yield {
            ctx.env.eventBus.publish(SignUpEvent(user, request, request2Messages))
            ctx.env.eventBus.publish(LoginEvent(user, request, request2Messages))
            val duration = (System.currentTimeMillis - startTime).toInt
            logRequest(request, Some(user.id), Some(authenticator.loginInfo), duration, authedResponse.header.status)
            authedResponse
          }
      }
      response
    }
  }

  def withAdminSession(action: String)(block: (SecuredRequest[AnyContent]) => Future[Result]) = SecuredAction.async { implicit request =>
    timing(action) {
      val startTime = System.currentTimeMillis
      if (request.identity.roles.contains(Role.Admin)) {
        block(request).map { r =>
          val duration = (System.currentTimeMillis - startTime).toInt
          logRequest(request, Some(request.identity.id), Some(request.authenticator.loginInfo), duration, r.header.status)
          r
        }
      } else {
        Future.successful(NotFound("404 Not Found"))
      }
    }
  }

  val anonymousId = UUID.fromString("00000000-0000-0000-0000-000000000000")
  val anonymousLoginInfo = LoginInfo("anonymous", "guest")

  private[this] def logRequest(request: RequestHeader, userId: Option[UUID], loginInfo: Option[LoginInfo], duration: Int, status: Int) = {
    val log = RequestLog(request, userId.getOrElse(anonymousId), loginInfo.getOrElse(anonymousLoginInfo), duration, status)
    RequestHistoryService.insert(log)
  }
}

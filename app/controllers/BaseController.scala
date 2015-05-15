package controllers

import java.util.UUID

import _root_.services.user.AuthenticationEnvironment
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.user.{ Role, User, WithRole }
import nl.grons.metrics.scala.Timer
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ AnyContent, Result }
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
    request.identity match {
      case Some(user) => block(SecuredRequest(user, request.authenticator.getOrElse(throw new IllegalStateException()), request))
      case None =>
        val user = User(
          id = UUID.randomUUID(),
          loginInfos = Nil,
          username = None,
          email = None,
          avatarUrl = None,
          firstName = None,
          lastName = None,
          fullName = None,
          gender = None,
          created = new LocalDateTime()
        )

        val loginInfo = LoginInfo("anonymous", user.id.toString)

        for {
          user <- env.identityService.save(user)
          authenticator <- env.authenticatorService.create(loginInfo)
          value <- env.authenticatorService.init(authenticator)
          authedResponse <- env.authenticatorService.embed(value, block(SecuredRequest(user, authenticator, request)))
        } yield {
          env.eventBus.publish(SignUpEvent(user, request, request2lang))
          env.eventBus.publish(LoginEvent(user, request, request2lang))
          authedResponse
        }
    }
  }
}

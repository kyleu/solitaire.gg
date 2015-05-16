package controllers.auth

import com.mohiva.play.silhouette.api.{ LoginEvent, LoginInfo, SignUpEvent }
import com.mohiva.play.silhouette.impl.providers.{CommonSocialProfile, CredentialsProvider}
import controllers.BaseController
import models.user.UserForms
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.user.AuthenticationEnvironment

import scala.concurrent.Future

object RegistrationController extends BaseController {
  private[this] val env = AuthenticationEnvironment

  def registrationForm = withSession { implicit request =>
    Future.successful(Ok(views.html.auth.register(UserForms.registrationForm)))
  }

  def register = withSession { implicit request =>
    UserForms.registrationForm.bindFromRequest.fold(
      form => Future.successful(BadRequest(views.html.auth.register(form))),
      data => {
        val loginInfo = LoginInfo(CredentialsProvider.ID, data.email)
        env.identityService.retrieve(loginInfo).flatMap {
          case Some(user) => Future.successful {
            Redirect(controllers.auth.routes.RegistrationController.registrationForm()).flashing("error" -> "The email address is already in use.")
          }
          case None =>
            val authInfo = env.hasher.hash(data.password)
            val user = request.identity.copy(
              username = Some(data.username),
              profiles = request.identity.profiles :+ loginInfo
            )
            val profile = CommonSocialProfile(
              loginInfo = loginInfo,
              email = Some(data.email)
            )
            val r = Future.successful(Redirect(controllers.routes.HomeController.index()))
            for {
              avatar <- env.avatarService.retrieveURL(data.email)
              profile <- env.identityService.create(user, profile)
              user <- env.identityService.save(user.copy(avatar = avatar.getOrElse("default")), update = true)
              authInfo <- env.authInfoService.save(loginInfo, authInfo)
              authenticator <- env.authenticatorService.create(loginInfo)
              value <- env.authenticatorService.init(authenticator)
              result <- env.authenticatorService.embed(value, r)
            } yield {
              env.eventBus.publish(SignUpEvent(user, request, request2lang))
              env.eventBus.publish(LoginEvent(user, request, request2lang))
              result
            }
        }
      }
    )
  }
}

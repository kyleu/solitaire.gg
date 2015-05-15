package controllers.auth

import com.mohiva.play.silhouette.api.{ LoginEvent, LoginInfo, SignUpEvent }
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
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
              loginInfos = request.identity.loginInfos :+ loginInfo,
              username = Some(data.username),
              email = Some(data.email),
              firstName = Some(data.firstName),
              lastName = Some(data.lastName),
              fullName = Some(data.firstName + " " + data.lastName)
            )
            val r = Future.successful(Redirect(controllers.routes.HomeController.index()))
            for {
              avatar <- env.avatarService.retrieveURL(data.email)
              user <- env.identityService.save(user.copy(avatarUrl = avatar), update = true)
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

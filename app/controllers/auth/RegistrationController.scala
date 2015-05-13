package controllers.auth

import java.util.UUID

import com.mohiva.play.silhouette.api.{LoginEvent, LoginInfo, SignUpEvent}
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import controllers.BaseController
import models.user.{BaseInfo, User, UserForms}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import services.user.AuthenticationEnvironment

import scala.concurrent.Future

object RegistrationController extends BaseController {
  private[this] val env = AuthenticationEnvironment

  def registrationForm = Action.async { implicit request =>
    Future.successful(Ok(views.html.auth.register(UserForms.registrationForm)))
  }

  def register = Action.async { implicit request =>
    UserForms.registrationForm.bindFromRequest.fold(
      form => Future.successful(BadRequest(views.html.auth.register(form))),
      data => {
        val loginInfo = LoginInfo(CredentialsProvider.ID, data.email)
        env.identityService.retrieve(loginInfo).flatMap {
          case Some(user) =>
            Future.successful(Redirect(controllers.auth.routes.RegistrationController.registrationForm()).flashing("error" -> "User already exists."))
          case None =>
            val authInfo = env.hasher.hash(data.password)
            val user = User(
              id = UUID.randomUUID(),
              loginInfo = loginInfo,
              username = Some(data.username),
              email = Some(data.email),
              avatarUrl = None,
              info = BaseInfo(
                firstName = Some(data.firstName),
                lastName = Some(data.lastName),
                fullName = Some(data.firstName + " " + data.lastName),
                gender = None
              )
            )
            val r = Future.successful(Redirect(controllers.routes.HomeController.index()))
            for {
              avatar <- env.avatarService.retrieveURL(data.email)
              user <- env.identityService.save(user.copy(avatarUrl = avatar))
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

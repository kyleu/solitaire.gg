package controllers.auth

import java.util.UUID

import com.mohiva.play.silhouette.api.{ LoginEvent, LoginInfo, SignUpEvent }
import com.mohiva.play.silhouette.impl.providers.{ CommonSocialProfile, CredentialsProvider }
import controllers.BaseController
import models.user.{ RegistrationData, User, UserForms }
import org.joda.time.LocalDateTime
import play.api.i18n.Messages
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.AnyContent
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
        env.identityService.retrieve(LoginInfo(CredentialsProvider.ID, data.email)).flatMap {
          case Some(user) => Future.successful {
            Ok(views.html.auth.register(UserForms.registrationForm.fill(data)))
            Redirect(controllers.auth.routes.RegistrationController.registrationForm()).flashing("error" -> Messages("registration.email.taken"))
          }
          case None => env.identityService.retrieve(data.username) flatMap {
            case Some(user) => Future.successful {
              Ok(views.html.auth.register(UserForms.registrationForm.fill(data)))
              Redirect(controllers.auth.routes.RegistrationController.registrationForm()).flashing("error" -> Messages("registration.username.taken"))
            }
            case None => saveProfile(data)
          }
        }
      }
    )
  }

  private[this] def saveProfile(data: RegistrationData)(implicit request: SecuredRequest[AnyContent]) = {
    val loginInfo = LoginInfo(CredentialsProvider.ID, data.email)
    val authInfo = env.hasher.hash(data.password)
    val user = if (request.identity.profiles.exists(_.providerID == "credentials")) {
      User(UUID.randomUUID, Some(data.username), profiles = Seq(loginInfo), created = new LocalDateTime(), avatar = "default")
    } else {
      request.identity.copy(
        username = if (data.username.isEmpty) { request.identity.username } else { Some(data.username) },
        profiles = request.identity.profiles :+ loginInfo
      )
    }
    val profile = CommonSocialProfile(
      loginInfo = loginInfo,
      email = Some(data.email)
    )
    val r = Future.successful(Redirect(controllers.routes.HomeController.index()))
    for {
      avatar <- env.avatarService.retrieveURL(data.email)
      profile <- env.identityService.create(user, profile.copy(avatarURL = avatar.orElse(Some("default"))))
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

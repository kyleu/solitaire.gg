package controllers.auth

import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.api.{ LoginEvent, LogoutEvent }
import com.mohiva.play.silhouette.impl.exceptions.IdentityNotFoundException
import com.mohiva.play.silhouette.impl.providers.{ CommonSocialProfile, SocialProvider, CommonSocialProfileBuilder }
import controllers.BaseController
import models.user.{ User, UserForms }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.user.AuthenticationEnvironment

import scala.concurrent.Future

object AuthorizationController extends BaseController {
  override val env = AuthenticationEnvironment

  def signInForm = withSession { implicit request =>
    Future.successful(Ok(views.html.auth.signin(UserForms.signInForm)))
  }

  def authenticateCredentials = withSession { implicit request =>
    UserForms.signInForm.bindFromRequest.fold(
      form => Future.successful(BadRequest(views.html.auth.signin(form))),
      credentials => env.credentials.authenticate(credentials).flatMap { loginInfo =>
        val result = Future.successful(Redirect(controllers.routes.HomeController.index()))
        env.identityService.retrieve(loginInfo).flatMap {
          case Some(user) => env.authenticatorService.create(loginInfo).flatMap { authenticator =>
            env.eventBus.publish(LoginEvent(user, request, request2lang))
            env.authenticatorService.init(authenticator).flatMap(v => env.authenticatorService.embed(v, result))
          }
          case None => Future.failed(new IdentityNotFoundException("Couldn't find user."))
        }
      }.recover {
        case e: ProviderException => Redirect(controllers.auth.routes.AuthorizationController.signInForm()).flashing("error" -> "Invalid Credentials.")
      }
    )
  }

  def authenticateSocial(provider: String) = withSession { implicit request =>
    (env.providers.get(provider) match {
      case Some(p: SocialProvider with CommonSocialProfileBuilder) =>
        p.authenticate().flatMap {
          case Left(result) => Future.successful(result)
          case Right(authInfo) => for {
            profile <- p.retrieveProfile(authInfo)
            user <- env.identityService.create(mergeUser(request.identity, profile), profile)
            authInfo <- env.authInfoService.save(profile.loginInfo, authInfo)
            authenticator <- env.authenticatorService.create(profile.loginInfo)
            value <- env.authenticatorService.init(authenticator)
            result <- env.authenticatorService.embed(value, Future.successful(Redirect(controllers.routes.HomeController.index())))
          } yield {
            env.eventBus.publish(LoginEvent(user, request, request2lang))
            result
          }
        }
      case _ => Future.failed(new ProviderException(s"Cannot authenticate with unexpected social provider $provider"))
    }).recover {
      case e: ProviderException =>
        logger.error("Unexpected provider error", e)
        Redirect(routes.AuthorizationController.signInForm()).flashing("error" -> "Could Not Authenticate.")
    }
  }

  def signOut = SecuredAction.async { implicit request =>
    val result = Future.successful(Redirect(controllers.routes.HomeController.index()))
    env.eventBus.publish(LogoutEvent(request.identity, request, request2lang))
    request.authenticator.discard(result)
  }

  private[this] def mergeUser(user: User, profile: CommonSocialProfile) = {
    user.copy(
      username = if (profile.firstName.isDefined && user.username.isEmpty) { profile.firstName } else { user.username },
      avatar = profile.avatarURL.getOrElse(user.avatar)
    )
  }
}

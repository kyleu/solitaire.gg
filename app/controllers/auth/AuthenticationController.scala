package controllers.auth

import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.api.{ LoginEvent, LogoutEvent }
import com.mohiva.play.silhouette.impl.providers.{ CommonSocialProfile, CommonSocialProfileBuilder, SocialProvider }
import controllers.BaseController
import models.user.User
import play.api.i18n.Messages
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.ApplicationContext

import scala.concurrent.Future

@javax.inject.Singleton
class AuthenticationController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def signInForm = withSession("form") { implicit request =>
    Future.successful(Ok(views.html.auth.signin(request.identity)))
  }

  def authenticateSocial(provider: String) = withSession("authenticate.social") { implicit request =>
    (ctx.env.providersMap.get(provider) match {
      case Some(p: SocialProvider with CommonSocialProfileBuilder) =>
        p.authenticate().flatMap {
          case Left(result) => Future.successful(result)
          case Right(authInfo) => for {
            profile <- p.retrieveProfile(authInfo)
            user <- ctx.env.userService.create(mergeUser(request.identity, profile), profile)
            authInfo <- ctx.env.authInfoService.save(profile.loginInfo, authInfo)
            authenticator <- ctx.env.authenticatorService.create(profile.loginInfo)
            value <- ctx.env.authenticatorService.init(authenticator)
            result <- ctx.env.authenticatorService.embed(value, Redirect(controllers.routes.HomeController.index()))
          } yield {
            ctx.env.eventBus.publish(LoginEvent(user, request, request2Messages))
            result
          }
        }
      case _ => Future.failed(new ProviderException(Messages("authentication.invalid.provider", provider)))
    }).recover {
      case e: ProviderException =>
        logger.error("Unexpected provider error", e)
        Redirect(routes.AuthenticationController.signInForm()).flashing("error" -> Messages("authentication.service.error", provider))
    }
  }

  def signOut = withSession("signout") { implicit request =>
    val result = Redirect(controllers.routes.HomeController.index())
    ctx.env.eventBus.publish(LogoutEvent(request.identity, request, request2Messages))
    ctx.env.authenticatorService.discard(request.authenticator, result).map(x => result)
  }

  private[this] def mergeUser(user: User, profile: CommonSocialProfile) = {
    user.copy(
      username = if (profile.firstName.isDefined && user.username.isEmpty) { profile.firstName } else { user.username },
      preferences = user.preferences.copy(avatar = profile.avatarURL.getOrElse(user.preferences.avatar))
    )
  }
}

package controllers

import models.database.queries.auth.{ UserQueries, ProfileQueries }
import models.user.Avatars
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.CacheService

import scala.concurrent.Future

class ProfileController @javax.inject.Inject() (val messagesApi: MessagesApi) extends BaseController {
  def profile = withSession { implicit request =>
    Database.query(ProfileQueries.FindProfilesByUser(request.identity.id)).map { profiles =>
      Ok(views.html.profile(request.identity, profiles))
    }
  }

  def updateAvatar(key: String) = withSession { implicit request =>
    val loginInfo = key match {
      case "facebook" => request.identity.profiles.find(_.providerID == "facebook")
      case "google" => request.identity.profiles.find(_.providerID == "google")
      case "twitter" => request.identity.profiles.find(_.providerID == "twitter")
      case "steam" => request.identity.profiles.find(_.providerID == "steam")
      case _ => None
    }
    val urlFuture = if (Avatars.all.isDefinedAt(key)) {
      Future.successful(Avatars.all(key))
    } else {
      loginInfo match {
        case Some(li) => Database.query(ProfileQueries.FindProfile(li.providerID, li.providerKey)).map(_.flatMap(_.avatarURL).getOrElse(Avatars.default))
        case None => throw new IllegalStateException("Cannot find avatar to match [" + key + "].")
      }
    }
    urlFuture.flatMap { url =>
      Database.execute(UserQueries.SetAvatarUrl(request.identity.id, url)).map { i =>
        CacheService.removeUser(request.identity.id)
        Redirect(controllers.routes.ProfileController.profile())
      }
    }
  }
}

package controllers

import models.database.queries.auth.{ UserQueries, ProfileQueries }
import models.user.Avatars
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.CacheService

import scala.concurrent.Future

object ProfileController extends BaseController {
  def profile = withSession { implicit request =>
    Database.query(ProfileQueries.FindProfilesByUser(request.identity.id)).map { profiles =>
      Ok(views.html.profile(request.identity, profiles))
    }
  }

  def setOption(option: String, value: String) = withSession { implicit request =>
    val f = option match {
      case "avatar" =>
        val loginInfo = value match {
          case "facebook" => request.identity.profiles.find(_.providerID == "facebook")
          case "google" => request.identity.profiles.find(_.providerID == "google")
          case "twitter" => request.identity.profiles.find(_.providerID == "twitter")
          case "steam" => request.identity.profiles.find(_.providerID == "steam")
          case _ => None
        }
        val urlFuture = if (Avatars.all.isDefinedAt(value)) {
          Future.successful(Avatars.all(value))
        } else {
          loginInfo match {
            case Some(li) => Database.query(ProfileQueries.FindProfile(li.providerID, li.providerKey)).map(_.flatMap(_.avatarURL).getOrElse(Avatars.default))
            case None => throw new IllegalStateException("Cannot find avatar to match [" + value + "].")
          }
        }
        urlFuture.flatMap { url =>
          Database.execute(UserQueries.SetAvatarUrl(request.identity.id, url)).map { i =>
            CacheService.removeUser(request.identity.id)
            Redirect(controllers.routes.ProfileController.profile())
          }
        }
      case "color" =>
        Database.execute(UserQueries.SetColor(request.identity.id, value)).map(x => Ok("OK"))
    }
    f.map { x =>
      CacheService.removeUser(request.identity.id)
      x
    }
  }
}

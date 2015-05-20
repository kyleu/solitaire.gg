package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.database.queries.RequestLogQueries
import models.database.queries.auth.UserQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.game.GameHistoryService
import services.user.UserService
import utils.CacheService

import scala.concurrent.Future

object UserController extends BaseController {
  def userList(q: String, sortBy: String) = AdminAction.async { implicit request =>
    Database.query(UserQueries.SearchUsers(q)).map { users =>
      Ok(views.html.admin.userList(q, sortBy, users))
    }
  }

  def userDetail(id: UUID, sortGamesBy: String) = AdminAction.async { implicit request =>
    UserService.retrieve(id).flatMap {
      case Some(user) => GameHistoryService.getByUser(id, sortGamesBy).flatMap { games =>
        Database.query(RequestLogQueries.FindRequestsByUser(id)).map { requests =>
          Ok(views.html.admin.userDetail(user, games, requests, ""))
        }
      }
      case None => Future.successful(NotFound("User [" + id + "] not found."))
    }
  }

  def removeUser(id: UUID) = AdminAction.async { implicit request =>
    Database.execute(UserQueries.RemoveUser(id)).map { i =>
      CacheService.removeUser(id)
      Redirect(controllers.admin.routes.UserController.userList(""))
    }
  }
}

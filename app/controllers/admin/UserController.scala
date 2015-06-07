package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.database.queries.RequestLogQueries
import models.database.queries.auth.UserQueries
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.game.GameHistoryService
import services.user.UserService
import utils.CacheService

import scala.concurrent.Future

class UserController @javax.inject.Inject() (val messagesApi: MessagesApi) extends BaseController {
  def userList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    Database.query(UserQueries.CountQuery(q)).flatMap { count =>
      Database.query(UserQueries.SearchQuery(q, getOrderClause(sortBy), Some(page))).map { users =>
        Ok(views.html.admin.user.userList(q, sortBy, count, page, users))
      }
    }
  }

  def userDetail(id: UUID, sortGamesBy: String) = withAdminSession { implicit request =>
    UserService.retrieve(id).flatMap {
      case Some(user) => GameHistoryService.getByUser(id, sortGamesBy).flatMap { games =>
        Database.query(RequestLogQueries.FindRequestsByUser(id)).map { requests =>
          Ok(views.html.admin.user.userDetail(user, games, requests, ""))
        }
      }
      case None => Future.successful(NotFound("User [" + id + "] not found."))
    }
  }

  def removeUser(id: UUID) = withAdminSession { implicit request =>
    Database.execute(UserQueries.RemoveById(Seq(id))).map { i =>
      CacheService.removeUser(id)
      Redirect(controllers.admin.routes.UserController.userList(""))
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "created" => "created desc"
    case x => x
  }
}

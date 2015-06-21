package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.database.queries.ReportQueries
import models.database.queries.auth.UserQueries
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.history.{ RequestHistoryService, GameHistoryService }

import scala.concurrent.Future

@javax.inject.Singleton
class UserController @javax.inject.Inject() (override val messagesApi: MessagesApi) extends BaseController {
  def userList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    for {
      count <- Database.query(UserQueries.count(q))
      users <- Database.query(UserQueries.search(q, getOrderClause(sortBy), Some(page)))
      gameCounts <- Database.query(new ReportQueries.GameCountForUsers(users.map(_.id)))
      winCounts <- Database.query(new ReportQueries.WinCountForUsers(users.map(_.id)))
      requestCounts <- Database.query(new ReportQueries.RequestCountForUsers(users.map(_.id)))
    } yield Ok(views.html.admin.user.userList(q, sortBy, count, page, users, gameCounts, winCounts, requestCounts))
  }

  def userDetail(id: UUID, sortGamesBy: String) = withAdminSession { implicit request =>
    env.identityService.retrieve(id).flatMap {
      case Some(user) => GameHistoryService.getByUser(id, sortGamesBy).flatMap { games =>
        RequestHistoryService.getByUser(id).map { requests =>
          Ok(views.html.admin.user.userDetail(user, games, requests, ""))
        }
      }
      case None => Future.successful(NotFound(s"User [$id] not found."))
    }
  }

  def removeUser(id: UUID) = withAdminSession { implicit request =>
    env.userService.remove(id).map { result =>
      Redirect(controllers.admin.routes.UserController.userList("")).flashing("success" -> s"User [$id] removed.")
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "created" => "created desc"
    case x => x
  }
}

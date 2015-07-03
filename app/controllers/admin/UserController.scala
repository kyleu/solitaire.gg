package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.database.queries.auth.UserQueries
import models.database.queries.report.ReportQueries
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.history.{ RequestHistoryService, GameHistoryService }
import services.user.AuthenticationEnvironment

import scala.concurrent.Future

@javax.inject.Singleton
class UserController @javax.inject.Inject() (
    override val messagesApi: MessagesApi,
    override val env: AuthenticationEnvironment
) extends BaseController {
  def userList(q: String, sortBy: String, page: Int) = withAdminSession { implicit request =>
    for {
      count <- Database.query(UserQueries.searchCount(q))
      users <- Database.query(UserQueries.search(q, getOrderClause(sortBy), Some(page)))
      gameCounts <- Database.query(new ReportQueries.GameCountForUsers(users.map(_.id)))
      winCounts <- Database.query(new ReportQueries.WinCountForUsers(users.map(_.id)))
      requestCounts <- Database.query(new ReportQueries.RequestCountForUsers(users.map(_.id)))
    } yield Ok(views.html.admin.user.userList(q, sortBy, count, page, users, gameCounts, winCounts, requestCounts))
  }

  def userDetail(id: UUID) = withAdminSession { implicit request =>
    env.identityService.retrieve(id).flatMap {
      case Some(user) => for {
        gameCount <- GameHistoryService.getCountByUser(id)
        requestCount <- RequestHistoryService.getCountByUser(id)
      } yield Ok(views.html.admin.user.userDetail(user, gameCount, requestCount))
      case None => Future.successful(NotFound(s"User [$id] not found."))
    }
  }

  def removeUser(id: UUID) = withAdminSession { implicit request =>
    env.userService.remove(id).map { result =>
      val success = if (result("users") == 1) { "successfully" } else { "with an error" }
      val profiles = result("profiles")
      val requests = result("requests")
      val games = result("games")
      val cards = result("cards")
      val moves = result("moves")
      val timing = result("timing")
      val msg = s"User [$id] removed $success in [${timing}ms]. " +
        s"Removed $profiles profiles, $requests requests, and $games games, along with their $cards cards and $moves moves."
      Redirect(controllers.admin.routes.UserController.userList("")).flashing("success" -> msg)
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "created" => "created desc"
    case x => x
  }
}

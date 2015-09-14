package controllers.admin

import java.util.UUID

import controllers.BaseController
import models.queries.report.ReportQueries
import models.queries.user.UserQueries
import models.user.Role
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.history.{ RequestHistoryService, GameHistoryService }
import utils.ApplicationContext
import utils.cache.UserCache

import scala.concurrent.Future

@javax.inject.Singleton
class UserController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def userList(q: String, sortBy: String, page: Int) = withAdminSession("list") { implicit request =>
    for {
      count <- Database.query(UserQueries.searchCount(q))
      users <- Database.query(UserQueries.search(q, getOrderClause(sortBy), Some(page)))
      gameCounts <- Database.query(new ReportQueries.GameCountForUsers(users.map(_.id)))
      winCounts <- Database.query(new ReportQueries.WinCountForUsers(users.map(_.id)))
      requestCounts <- Database.query(new ReportQueries.RequestCountForUsers(users.map(_.id)))
    } yield Ok(views.html.admin.user.userList(q, sortBy, count, page, users, gameCounts, winCounts, requestCounts))
  }

  def userDetail(id: UUID) = withAdminSession("detail") { implicit request =>
    ctx.env.identityService.retrieve(id).flatMap {
      case Some(user) => for {
        gameCount <- GameHistoryService.getCountByUser(id)
        requestCount <- RequestHistoryService.getCountByUser(id)
      } yield Ok(views.html.admin.user.userDetail(user, gameCount, requestCount, request.identity.id))
      case None => Future.successful(NotFound(s"User [$id] not found."))
    }
  }

  def removeUser(id: UUID) = withAdminSession("remove") { implicit request =>
    ctx.env.userService.remove(id).map { result =>
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


  def makeAdmin(id: UUID) = withAdminSession("detail") { implicit request =>
    ctx.env.identityService.retrieve(id).flatMap {
      case Some(user) =>
        val msg = s"User [${user.id}] has been given the admin role."
        Database.execute(UserQueries.AddRole(user.id, Role.Admin)).map { x =>
          UserCache.removeUser(user.id)
          Redirect(controllers.admin.routes.UserController.userDetail(id)).flashing("success" -> msg)
        }
      case None => Future.successful(NotFound(s"User [$id] not found."))
    }
  }

  def removeAdmin(id: UUID) = withAdminSession("detail") { implicit request =>
    ctx.env.identityService.retrieve(id).flatMap {
      case Some(user) =>
        val msg = s"User [${user.id}] has had the admin role removed."
        Database.execute(UserQueries.RemoveRole(user.id, Role.Admin)).map { x =>
          UserCache.removeUser(user.id)
          Redirect(controllers.admin.routes.UserController.userDetail(id)).flashing("success" -> msg)
        }
      case None => Future.successful(NotFound(s"User [$id] not found."))
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case "created" => "created desc"
    case x => x
  }
}

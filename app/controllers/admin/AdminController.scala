package controllers.admin

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import controllers.BaseController
import controllers.BaseController.AdminAction
import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.account.AccountService
import services.ActorSupervisor
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

object AdminController extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def enable = AdminAction.async { implicit request =>
    Future {
      AccountService.updateAccountRole(request.accountId, "admin")
      Ok("OK").withSession(request.session + ("role" -> "admin"))
    }
  }


  def status = AdminAction.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case x: SystemStatus => Ok(views.html.admin.status(x))
    }
  }

  def observeRandomGame() = AdminAction.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case ss: SystemStatus => if (ss.games.isEmpty) {
        Ok("No games available.")
      } else {
        val gameId = ss.games(new Random().nextInt(ss.games.length))._1
        Ok(views.html.game.gameplay(request.accountId, request.name, "", Seq("observe", gameId.toString)))
      }
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def observeGameAsAdmin(gameId: UUID) = AdminAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, "", Seq("observe", gameId.toString)))
  }

  def observeGameAs(gameId: UUID, as: UUID) = AdminAction { implicit request =>
    Ok(views.html.game.gameplay(request.accountId, request.name, "", Seq("observe", gameId.toString, as.toString)))
  }
}

package controllers.admin

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.{ Testbed, AccountService, ActorSupervisor }
import utils.parser.PolitaireParser

import scala.concurrent.duration._
import scala.util.Random

object AdminController extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def enable = AuthenticatedAction { implicit request =>
    AccountService.updateAccountRole(request.accountId, "admin")
    Ok("OK").withSession(request.session + ("role" -> "admin"))
  }

  def testbed = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(Testbed.go())
    }
  }

  def politaire = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.politaireList(PolitaireParser.politaireList))
    }
  }

  def rules = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.rulesList(PolitaireParser.gameRules))
    }
  }

  def status = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case x: SystemStatus => requireAdmin {
        Ok(views.html.admin.status(x))
      }
    }
  }

  def observeRandomGame() = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case ss: SystemStatus => requireAdmin {
        if (ss.games.isEmpty) {
          Ok("No games available.")
        } else {
          val gameId = ss.games(new Random().nextInt(ss.games.length))._1
          Ok(views.html.admin.observeGame(request.accountId, request.name, Some(gameId), None))
        }
      }
      case se: ServerError => requireAdmin {
        Ok(se.reason + ": " + se.content)
      }
    }
  }

  def observeGameAsAdmin(gameId: UUID) = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.observeGame(request.accountId, request.name, Some(gameId), None))
    }
  }

  def observeGameAs(gameId: UUID, as: UUID) = AuthenticatedAction { implicit request =>
    requireAdmin {
      Ok(views.html.admin.observeGame(request.accountId, request.name, Some(gameId), Some(as)))
    }
  }
}

package controllers.admin

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import controllers.BaseController
import controllers.BaseController.AuthenticatedAction
import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.{ AccountService, ActorSupervisor }

import scala.concurrent.duration._
import scala.util.Random

object AdminController extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def enable = AuthenticatedAction { implicit request =>
    AccountService.updateAccountRole(request.accountId, "admin")
    Ok("OK").withSession(request.session + ("role" -> "admin"))
  }

  def status = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case x: SystemStatus => requireAdmin {
        Ok(views.html.admin.status(x))
      }
    }
  }

  def traceConnection(connectionId: UUID) = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask ConnectionTrace(connectionId)).map {
      case tr: TraceResponse => requireAdmin {
        Ok(views.html.admin.trace("Connection", tr))
      }
      case se: ServerError => requireAdmin {
        Ok(se.reason + ": " + se.content)
      }
    }
  }

  def traceClient(connectionId: UUID) = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask ClientTrace(connectionId)).map {
      case tr: TraceResponse => requireAdmin {
        Ok(views.html.admin.trace("Client", tr))
      }
      case se: ServerError => requireAdmin {
        Ok(se.reason + ": " + se.content)
      }
    }
  }

  def traceGame(gameId: UUID) = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask GameTrace(gameId)).map {
      case tr: TraceResponse => requireAdmin {
        val buttons = Seq("Observe As Admin" -> controllers.admin.routes.AdminController.observeGameAsAdmin(gameId).url)
        Ok(views.html.admin.trace("Game", tr, buttons))
      }
      case se: ServerError => requireAdmin {
        Ok(se.reason + ": " + se.content)
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

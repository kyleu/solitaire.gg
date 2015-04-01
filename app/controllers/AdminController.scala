package controllers

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import models._
import play.api.mvc._
import services.{ TestService, ActorSupervisor }
import scala.concurrent.duration._

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.util.Random

object AdminController extends Controller {
  implicit val timeout = Timeout(10.seconds)

  def index = Action { implicit request =>
    Ok(views.html.admin.admin())
  }

  def status = Action.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case x: SystemStatus => Ok(views.html.admin.status(x))
    }
  }

  def traceConnection(connectionId: UUID) = Action.async { implicit request =>
    (ActorSupervisor.instance ask ConnectionTrace(connectionId)).map {
      case tr: TraceResponse => Ok(views.html.admin.trace("Connection", tr))
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def traceClient(connectionId: UUID) = Action.async { implicit request =>
    (ActorSupervisor.instance ask ClientTrace(connectionId)).map {
      case tr: TraceResponse => Ok(views.html.admin.trace("Client", tr))
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def traceGame(gameId: UUID) = Action.async { implicit request =>
    (ActorSupervisor.instance ask GameTrace(gameId)).map {
      case tr: TraceResponse =>
        val buttons = Seq("Observe As Admin" -> routes.AdminController.observeGameAsAdmin(gameId).url)
        Ok(views.html.admin.trace("Game", tr, buttons))
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def observeRandomGame() = Action.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case ss: SystemStatus => if (ss.games.isEmpty) {
        Ok("No games available.")
      } else {
        val gameId = ss.games(new Random().nextInt(ss.games.length))._1
        Ok(views.html.admin.observeGame(Some(gameId), None))
      }
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def observeGameAsAdmin(gameId: UUID) = Action { implicit request =>
    Ok(views.html.admin.observeGame(Some(gameId), None))
  }

  def observeGameAs(gameId: UUID, as: UUID) = Action { implicit request =>
    Ok(views.html.admin.observeGame(Some(gameId), Some(as)))
  }

  def runTest(test: String) = Action { implicit request =>
    val ret = test match {
      case "all" => TestService.testAll()
      case "account" => TestService.testAccount()
      case "variants" => TestService.testVariants()
      case "known" => TestService.testKnownGame()
      case x => TestService.testVariant(x)
    }
    Ok(views.html.admin.testResults(test, ret.toString(0)))
  }
}

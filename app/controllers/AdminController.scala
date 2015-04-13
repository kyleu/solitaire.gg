package controllers

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import controllers.BaseController.AuthenticatedAction
import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.{ AccountService, ActorSupervisor }
import services.test.TestService

import scala.concurrent.duration._
import scala.util.Random

object AdminController extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def index = AuthenticatedAction { implicit request =>
    Ok(views.html.admin.admin())
  }

  def accountList(q: String) = AuthenticatedAction { implicit request =>
    val accounts = AccountService.searchAccounts(q)
    Ok(views.html.admin.accountList(q, accounts))
  }

  def removeAccount(id: UUID) = AuthenticatedAction { implicit request =>
    AccountService.removeAccount(id)
    Redirect(routes.AdminController.accountList(""))
  }

  def status = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case x: SystemStatus => Ok(views.html.admin.status(x))
    }
  }

  def traceConnection(connectionId: UUID) = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask ConnectionTrace(connectionId)).map {
      case tr: TraceResponse => Ok(views.html.admin.trace("Connection", tr))
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def traceClient(connectionId: UUID) = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask ClientTrace(connectionId)).map {
      case tr: TraceResponse => Ok(views.html.admin.trace("Client", tr))
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def traceGame(gameId: UUID) = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask GameTrace(gameId)).map {
      case tr: TraceResponse =>
        val buttons = Seq("Observe As Admin" -> routes.AdminController.observeGameAsAdmin(gameId).url)
        Ok(views.html.admin.trace("Game", tr, buttons))
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def observeRandomGame() = AuthenticatedAction.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case ss: SystemStatus => if (ss.games.isEmpty) {
        Ok("No games available.")
      } else {
        val gameId = ss.games(new Random().nextInt(ss.games.length))._1
        Ok(views.html.admin.observeGame(request.accountId, request.name, Some(gameId), None))
      }
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }

  def observeGameAsAdmin(gameId: UUID) = AuthenticatedAction { implicit request =>
    Ok(views.html.admin.observeGame(request.accountId, request.name, Some(gameId), None))
  }

  def observeGameAs(gameId: UUID, as: UUID) = AuthenticatedAction { implicit request =>
    Ok(views.html.admin.observeGame(request.accountId, request.name, Some(gameId), Some(as)))
  }

  def runTest(test: String) = AuthenticatedAction { implicit request =>
    val ts = new TestService()
    val ret = test match {
      case "all" => ts.testAll()
      case "account" => ts.testAccount()
      case "known" => ts.testKnownGame()
      case "solver" => ts.testSolver()
      case "variants" => ts.testVariants()
      case x => ts.testVariant(x)
    }
    Ok(views.html.admin.testResults(test, ret.toString(0)))
  }
}

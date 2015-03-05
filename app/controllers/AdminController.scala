package controllers

import akka.pattern.ask
import akka.util.Timeout
import models._
import play.api.mvc._
import services.ActorSupervisor
import scala.concurrent.duration._

import play.api.libs.concurrent.Execution.Implicits.defaultContext

object AdminController extends Controller {
  implicit val timeout = Timeout(10.seconds)

  def index = Action { implicit request =>
    Ok(views.html.admin.admin())
  }

  def test = Action { implicit request =>
    Ok(views.html.admin.test())
  }

  def status = Action.async { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map { x =>
      Ok(views.html.admin.status(x.asInstanceOf[SystemStatus]))
    }
  }

  def traceGame(id: String) = Action.async { implicit request =>
    (ActorSupervisor.instance ask GameTrace(id)).map { x =>
      Ok(views.html.admin.trace("Game", id, x.asInstanceOf[TraceResponse]))
    }
  }

  def traceConnection(id: String) = Action.async { implicit request =>
    (ActorSupervisor.instance ask ConnectionTrace(id)).map {
      case tr: TraceResponse => Ok(views.html.admin.trace("Connection", id, tr))
      case se: ServerError => Ok(se.reason + ": " + se.content)
    }
  }
}

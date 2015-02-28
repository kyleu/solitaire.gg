package controllers

import akka.pattern.ask
import akka.util.Timeout
import models.{SystemStatus, GetSystemStatus}
import play.api.mvc._
import services.ActorSupervisor
import scala.concurrent.duration._

object AdminController extends Controller {
  implicit val timeout = Timeout(10.seconds)

  def index = Action { implicit request =>
    Ok(views.html.admin())
  }

  def test = Action { implicit request =>
    Ok(views.html.test())
  }

  def status = Action.async { implicit request =>
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    (ActorSupervisor.instance ask GetSystemStatus).map { x =>
      Ok(views.html.status(x.asInstanceOf[SystemStatus]))
    }
  }
}

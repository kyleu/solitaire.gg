package controllers

import play.api.i18n.I18nSupport
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import utils.metrics.Instrumented
import utils.{Application, Logging}

import scala.concurrent.Future

object BaseController extends Logging {
  val adminKey = Option(System.getenv("ADMIN_KEY")).getOrElse {
    log.warn("Using default admin key. This installation is not secure.")
    "admin"
  }
}

abstract class BaseController() extends Controller with I18nSupport with Instrumented with Logging {
  def app: Application

  override def messagesApi = app.messagesApi

  def req(action: String)(block: (Request[AnyContent]) => Future[Result]) = Action.async { implicit request =>
    metrics.timer(action).timeFuture(block(request))
  }

  def withAdminSession(action: String)(block: (Request[AnyContent]) => Future[Result]) = Action.async { implicit request =>
    val cookie = request.cookies.get("role").map(_.value)
    if (cookie.contains(BaseController.adminKey)) {
      metrics.timer(action).timeFuture(block(request))
    } else {
      Future.successful(NotFound("404 Not Found"))
    }
  }
}

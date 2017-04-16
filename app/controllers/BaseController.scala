package controllers

import models.history.RequestLog
import play.api.i18n.I18nSupport
import services.history.RequestHistoryService
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
    val startTime = System.currentTimeMillis
    val response = metrics.timer(action).timeFuture {
      block(request).map { r =>
        val duration = (System.currentTimeMillis - startTime).toInt
        logRequest(request, duration, r.header.status)
        r
      }
    }
    response
  }

  def withAdminSession(action: String)(block: (Request[AnyContent]) => Future[Result]) = Action.async { implicit request =>
    val startTime = System.currentTimeMillis
    val cookie = request.cookies.get("role").map(_.value)
    if (cookie.contains(BaseController.adminKey)) {
      metrics.timer(action).timeFuture {
        block(request).map { r =>
          val duration = (System.currentTimeMillis - startTime).toInt
          logRequest(request, duration, r.header.status)
          r
        }
      }
    } else {
      Future.successful(NotFound("404 Not Found"))
    }
  }

  private[this] def logRequest(request: RequestHeader, duration: Int, status: Int) = {
    val log = RequestLog(request, duration, status)
    RequestHistoryService.insert(log)
  }
}

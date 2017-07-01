package controllers

import java.util.UUID

import play.api.i18n.I18nSupport
import utils.FutureUtils.defaultContext
import play.api.mvc._
import services.user.UserService
import utils.metrics.Instrumented
import utils.{Application, Logging}

import scala.concurrent.Future

object BaseController extends Logging {
  val adminKey = Option(System.getenv("ADMIN_KEY")).getOrElse {
    log.warn("Using default admin key. This installation is not secure.")
    "admin"
  }
}

abstract class BaseController() extends InjectedController with I18nSupport with Instrumented with Logging {
  def app: Application

  override implicit val messagesApi = app.messagesApi

  protected[this] def getUser(request: RequestHeader) = request.session.get("userId") match {
    case Some(id) if id.length == 36 => UserService.getById(UUID.fromString(id)).flatMap {
      case Some(user) => Future.successful(user)
      case None => UserService.newUser()
    }
    case Some(_) => UserService.newUser()
    case None => UserService.newUser()
  }

  def req(action: String)(block: (Request[AnyContent]) => Future[Result]) = Action.async(parse.anyContent) { implicit request =>
    metrics.timer(action).timeFuture(block(request))
  }

  def withAdminSession(action: String)(block: (Request[AnyContent]) => Future[Result]) = Action.async(parse.anyContent) { implicit request =>
    val cookie = request.cookies.get("role").map(_.value)
    if (cookie.contains(BaseController.adminKey)) {
      metrics.timer(action).timeFuture(block(request))
    } else {
      Future.successful(NotFound("404 Not Found"))
    }
  }
}

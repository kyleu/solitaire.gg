package controllers

import java.util.UUID

import io.prometheus.client.Histogram
import play.api.i18n.I18nSupport
import util.FutureUtils.defaultContext
import play.api.mvc._
import services.user.UserService
import util.metrics.Instrumented
import util.{Application, Logging}

import scala.concurrent.Future

abstract class BaseController() extends InjectedController with I18nSupport with Logging {
  private[this] def cn(x: Any) = x.getClass.getSimpleName.replaceAllLiterally("$", "")

  def app: Application

  protected[this] lazy val metricsName = util.Config.projectId + "_" + cn(this)
  protected[this] lazy val requestHistogram = Histogram.build(
    metricsName + "_request",
    s"Controller request metrics for [$metricsName]"
  ).labelNames("method").register()

  override implicit val messagesApi = app.messagesApi

  protected[this] def getUserOpt(request: RequestHeader) = request.session.get("userId") match {
    case Some(id) if id.length == 36 => UserService.getById(UUID.fromString(id))
    case _ => Future.successful(None)
  }

  protected[this] def getUser(request: RequestHeader) = getUserOpt(request).flatMap {
    case Some(u) => Future.successful(u)
    case _ => UserService.newUser()
  }

  protected[this] def getAdminUser(request: RequestHeader) = getUserOpt(request).map(_.getOrElse(throw new IllegalStateException("Missing admin cookie.")))

  def req(action: String)(block: (Request[AnyContent]) => Future[Result]) = Action.async(parse.anyContent) { implicit request =>
    Instrumented.timeFuture(requestHistogram, action)(block(request))
  }

  def withAdminSession(action: String)(block: (Request[AnyContent]) => Future[Result]) = Action.async(parse.anyContent) { implicit request =>
    val cookie = request.cookies.get("role").map(_.value)
    if (cookie.contains(app.config.adminKey)) {
      Instrumented.timeFuture(requestHistogram, action)(block(request))
    } else {
      Future.successful(NotFound("404 Not Found"))
    }
  }
}

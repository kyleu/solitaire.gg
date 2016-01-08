package controllers

import java.util.UUID

import models.audit.AnalyticsEvent
import models.audit.AnalyticsEvent.EventType
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{ JsValue, Json }
import play.api.mvc.Action
import services.audit.AnalyticsService
import utils.ApplicationContext

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def preflightCheck(path: String) = Action.async { request =>
    val origin = request.headers.get("Origin").getOrElse("http://solitaire.gg")
    Future.successful(Ok("OK").withHeaders(
      "Access-Control-Allow-Origin" -> origin,
      "Access-Control-Allow-Credentials" -> "true",
      "Access-Control-Allow-Methods" -> "POST",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept"
    ))
  }

  def install(device: UUID) = analyticsAction(EventType.Install, device, AnalyticsService.install)
  def open(device: UUID) = analyticsAction(EventType.Open, device, AnalyticsService.open)
  def gameStart(device: UUID) = analyticsAction(EventType.GameStart, device, AnalyticsService.gameStart)
  def gameWon(device: UUID) = analyticsAction(EventType.GameWon, device, AnalyticsService.gameWon)
  def gameResigned(device: UUID) = analyticsAction(EventType.GameResigned, device, AnalyticsService.gameResigned)

  private[this] def analyticsAction(
    eventType: EventType,
    device: UUID,
    f: (UUID, JsValue) => Future[AnalyticsEvent]
  ) = withSession(eventType.id) { implicit request =>
    request.body.asJson match {
      case Some(json) => f(device, json).map { result =>
        val ret = Json.toJson(Map("status" -> "ok", "id" -> result.id.toString))
        Ok(ret).withHeaders("Access-Control-Allow-Origin" -> "*/*")
      }
      case None =>
        val ret = Json.toJson(Map("status" -> "error", "message" -> "A valid json request body is required."))
        Future.successful(BadRequest(ret).withHeaders("Access-Control-Allow-Origin" -> "*/*"))
    }
  }
}

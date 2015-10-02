package controllers

import java.util.UUID

import models.audit.AnalyticsEvent
import models.audit.AnalyticsEvent.EventType
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{ JsValue, Json }
import services.audit.AnalyticsService
import utils.ApplicationContext

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  def install(device: UUID) = analyticsAction(EventType.Install, device, AnalyticsService.gameStart)
  def gameStart(device: UUID) = analyticsAction(EventType.GameStart, device, AnalyticsService.gameStart)
  def gameWon(device: UUID) = analyticsAction(EventType.GameWon, device, AnalyticsService.gameStart)
  def gameResigned(device: UUID) = analyticsAction(EventType.GameResigned, device, AnalyticsService.gameStart)

  private[this] def analyticsAction(
    eventType: EventType,
    device: UUID,
    f: (UUID, JsValue) => Future[AnalyticsEvent]
  ) = withSession(eventType.id) { implicit request =>
    request.body.asJson match {
      case Some(json) => f(device, json).map { result =>
        Ok(Json.toJson(Map("status" -> "ok", "id" -> result.id.toString)))
      }
      case None => Future.successful(BadRequest(Json.toJson(Map("status" -> "error", "message" -> "A valid json request body is required."))))
    }
  }
}

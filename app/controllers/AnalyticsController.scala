package controllers

import java.util.UUID

import models.audit.AnalyticsEvent.EventType
import utils.Application

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsController @javax.inject.Inject() (override val app: Application) extends BaseController {
  private[this] val notifications = new AnalyticsNotifications(app.notificationService)

  def preflightCheck(path: String) = Action.async(parse.anyContent) { request =>
    val origin = request.headers.get("Origin").getOrElse("http://solitaire.gg")
    Future.successful(Ok("OK").withHeaders(
      "Access-Control-Allow-Origin" -> origin,
      "Access-Control-Allow-Credentials" -> "true",
      "Access-Control-Allow-Methods" -> "POST",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept"
    ))
  }

  def error(device: UUID) = analyticsAction(EventType.Error)
  def install(device: UUID) = analyticsAction(EventType.Install)
  def open(device: UUID) = analyticsAction(EventType.Open)
  def gameStart(device: UUID) = analyticsAction(EventType.GameStart)
  def gameWon(device: UUID) = analyticsAction(EventType.GameWon)
  def gameResigned(device: UUID) = analyticsAction(EventType.GameResigned)

  private[this] def analyticsAction(eventType: EventType) = req(eventType.value) { implicit request =>
    log.info(s"Analytics event received of type [$eventType].")
    Future.successful(Ok("{ \"status\": \"ok\" }").as("application/json"))
  }
}

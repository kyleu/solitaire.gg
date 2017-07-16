package controllers

import java.util.UUID

import util.Application

import scala.concurrent.Future

@javax.inject.Singleton
class AnalyticsController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def preflightCheck(path: String) = Action.async(parse.anyContent) { request =>
    val origin = request.headers.get("Origin").getOrElse("http://solitaire.gg")
    Future.successful(Ok("OK").withHeaders(
      "Access-Control-Allow-Origin" -> origin,
      "Access-Control-Allow-Credentials" -> "true",
      "Access-Control-Allow-Methods" -> "POST",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept"
    ))
  }

  def error(device: UUID) = analyticsAction("Error")
  def install(device: UUID) = analyticsAction("Install")
  def open(device: UUID) = analyticsAction("Open")
  def gameStart(device: UUID) = analyticsAction("GameStart")
  def gameWon(device: UUID) = analyticsAction("GameWon")
  def gameResigned(device: UUID) = analyticsAction("GameResigned")

  private[this] def analyticsAction(eventType: String) = req(s"analytics.$eventType") { implicit request =>
    log.info(s"Analytics event received of type [$eventType].")
    Future.successful(Ok("{ \"status\": \"ok\" }").as("application/json"))
  }
}

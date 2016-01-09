package controllers

import models.audit.AnalyticsEvent
import models.audit.AnalyticsEvent.EventType
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsObject
import services.audit.NotificationService
import utils.Logging

import scala.concurrent.Future

class AnalyticsNotifications(notificationService: NotificationService) extends Logging {
  def notify(eventType: EventType, result: AnalyticsEvent) = Future {
    eventType match {
      case EventType.Install => notificationService.alert(installMessage(result), "#production-installs")
      case EventType.Open => notificationService.alert(openMessage(result), "#production-installs")
      case EventType.GameStart => notificationService.alert(gameStartMessage(result), "#production-games")
      case EventType.GameWon => notificationService.alert(gameWonMessage(result), "#production-games")
      case EventType.GameResigned => notificationService.alert(gameResignedMessage(result), "#production-games")
      case _ => log.warn(s"Unhandled event type [$eventType].")
    }
  }

  def installMessage(result: AnalyticsEvent) = {
    s"Installed on device [${result.device}]."
  }

  def openMessage(result: AnalyticsEvent) = {
    s"Opened by device [${result.device}]."
  }

  def gameStartMessage(result: AnalyticsEvent) = {
    val gameId = (result.data \ "gameId").asOpt[String].getOrElse("?")
    val rules = (result.data \ "rules").asOpt[String].getOrElse("?")
    s"Started [$rules] game [$gameId] for device [${result.device}]."
  }

  def gameWonMessage(result: AnalyticsEvent) = {
    val message = (result.data \ "message").as[JsObject]
    val gameId = (message \ "id").asOpt[String].getOrElse("?")
    val rules = (message \ "rules").asOpt[String].getOrElse("?")
    val gameResult = (message \ "result").asOpt[JsObject].getOrElse(throw new IllegalStateException())
    val moves = (gameResult \ "moves").asOpt[Int].getOrElse(-1)
    val duration = (gameResult \ "durationSeconds").asOpt[Int].getOrElse(-1)
    s"Won [$rules] game [$gameId] in [$duration] seconds and [$moves] moves for device [${result.device}]."
  }

  def gameResignedMessage(result: AnalyticsEvent) = {
    val message = (result.data \ "message").as[JsObject]
    val gameId = (message \ "id").asOpt[String].getOrElse("?")
    val rules = (message \ "rules").asOpt[String].getOrElse("?")
    val gameResult = (message \ "result").asOpt[JsObject].getOrElse(throw new IllegalStateException())
    val moves = (gameResult \ "moves").asOpt[Int].getOrElse(-1)
    val duration = (gameResult \ "durationSeconds").asOpt[Int].getOrElse(-1)
    s"Resigned [$rules] game [$gameId] after [$duration] seconds and [$moves] moves for device [${result.device}]."
  }
}

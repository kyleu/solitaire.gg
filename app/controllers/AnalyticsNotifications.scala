package controllers

import models.audit.AnalyticsEvent
import models.audit.AnalyticsEvent.EventType
import services.audit.NotificationService
import utils.Logging

class AnalyticsNotifications(notificationService: NotificationService) extends Logging {
  def notify(eventType: EventType, result: AnalyticsEvent) = eventType match {
    case EventType.Install => notificationService.alert(installMessage(result), "#production-installs")
    case EventType.Open => notificationService.alert(openMessage(result), "#production-installs")
    case EventType.GameStart => notificationService.alert(gameStartMessage(result), "#production-games")
    case EventType.GameWon => notificationService.alert(gameWonMessage(result), "#production-games")
    case EventType.GameResigned => notificationService.alert(gameResignedMessage(result), "#production-games")
    case _ => log.warn(s"Unhandled event type [$eventType].")
  }

  def installMessage(result: AnalyticsEvent) = {
    s"[Install] for device [${result.device}]."
  }

  def openMessage(result: AnalyticsEvent) = {
    s"[Open] by device [${result.device}]."
  }

  def gameStartMessage(result: AnalyticsEvent) = {
    s"[Game Started] for device [${result.device}]."
  }

  def gameWonMessage(result: AnalyticsEvent) = {
    s"[Game Won] for device [${result.device}]."
  }

  def gameResignedMessage(result: AnalyticsEvent) = {
    s"[Game Resigned] for device [${result.device}]."
  }
}

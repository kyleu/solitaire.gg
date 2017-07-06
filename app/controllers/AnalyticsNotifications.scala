package controllers

import models.audit.AnalyticsEvent.EventType
import services.audit.NotificationService
import utils.FutureUtils.defaultContext
import utils.Logging

import scala.concurrent.Future

class AnalyticsNotifications(notificationService: NotificationService) extends Logging {
  def notify(eventType: EventType) = Future {
    eventType match {
      case EventType.Error => notificationService.alert(errorMessage(), "#production-errors")
      case EventType.Install => notificationService.alert(installMessage(), "#production-installs")
      case EventType.Open => notificationService.alert(openMessage(), "#production-installs")
      case EventType.GameStart => notificationService.alert(gameStartMessage(), "#production-games")
      case EventType.GameWon => notificationService.alert(gameWonMessage(), "#production-games")
      case EventType.GameResigned => notificationService.alert(gameResignedMessage(), "#production-games")
      case _ => log.warn(s"Unhandled event type [$eventType].")
    }
  }

  def errorMessage() = s"Errored."
  def installMessage() = s"Installed."
  def openMessage() = s"Opened."
  def gameStartMessage() = s"Started game."
  def gameWonMessage() = s"Won game."
  def gameResignedMessage() = s"Resigned game."
}

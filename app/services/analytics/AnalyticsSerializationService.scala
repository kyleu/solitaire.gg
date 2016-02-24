package services.analytics

import models.audit.AnalyticsEvent
import utils.Logging

object AnalyticsSerializationService extends Logging {
  def toEvent(e: AnalyticsEvent) = {
    e
  }
}

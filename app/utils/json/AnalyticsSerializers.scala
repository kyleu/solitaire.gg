package utils.json

import models.audit.AnalyticsEvent
import org.joda.time.LocalDateTime
import play.api.libs.json._

object AnalyticsSerializers {
  private[this] implicit val localDateTimeWrites = new Writes[LocalDateTime] {
    override def writes(ldt: LocalDateTime) = JsString(ldt.toString)
  }

  private[this] implicit val eventTypeWrites = new Writes[AnalyticsEvent.EventType] {
    override def writes(et: AnalyticsEvent.EventType) = JsString(et.toString)
  }

  implicit val eventWrites = Json.writes[AnalyticsEvent]
}

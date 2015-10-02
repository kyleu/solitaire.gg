package models.audit

import java.util.UUID

import org.joda.time.LocalDateTime
import play.api.libs.json.JsValue

object AnalyticsEvent {
  sealed abstract class EventType(val id: String)

  object EventType {
    case object Install extends EventType("install")
    case object GameStart extends EventType("game-start")
    case object GameWon extends EventType("game-won")
    case object GameResigned extends EventType("game-start")
    case class Unknown(override val id: String) extends EventType(id)

    val all = Seq(Install, GameStart)
    def fromString(s: String) = all.find(_.id == s).getOrElse(Unknown(s))
  }
}

case class AnalyticsEvent(
  id: UUID,
  eventType: AnalyticsEvent.EventType,
  device: UUID,
  data: JsValue,
  created: LocalDateTime
)

package models.audit

import java.util.UUID

import enumeratum._
import org.joda.time.LocalDateTime
import play.api.libs.json.JsValue

object AnalyticsEvent {
  sealed abstract class EventType(val id: String) extends EnumEntry

  object EventType extends Enum[EventType] {
    case object Error extends EventType("error")
    case object Install extends EventType("install")
    case object Open extends EventType("open")
    case object GameStart extends EventType("game-start")
    case object GameWon extends EventType("game-won")
    case object GameResigned extends EventType("game-resigned")
    case class Unknown(override val id: String) extends EventType(id)

    val all = Seq(Error, Install, Open, GameStart, GameWon, GameResigned)
    def keyMap = (all.map(x => x.id -> x) ++ all.map(x => x.toString -> x)).toMap
    def fromString(s: String) = keyMap.getOrElse(s, Unknown(s))

    override def values = findValues
  }
}

case class AnalyticsEvent(
  id: UUID,
  eventType: AnalyticsEvent.EventType,
  device: UUID,
  sourceAddress: Option[String],
  data: JsValue,
  created: LocalDateTime
)

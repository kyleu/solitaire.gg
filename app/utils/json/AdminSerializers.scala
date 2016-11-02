package utils.json

import models.user.UserStatistics
import models.{GameWon, GameLost, GameResult}
import models.analytics._
import models.audit.AnalyticsEvent
import models.audit.AnalyticsEvent.EventType
import org.joda.time.LocalDateTime
import play.api.libs.json._

object AdminSerializers {
  implicit val localDateTimeReads = new Reads[LocalDateTime] {
    override def reads(json: JsValue) = json match {
      case s: JsString => JsSuccess(LocalDateTime.parse(s.value))
      case x => JsError(x.toString)
    }
  }
  implicit val localDateTimeWrites = new Writes[LocalDateTime] {
    override def writes(ldt: LocalDateTime) = JsString(ldt.toString)
  }

  implicit val eventTypeReads = new Reads[EventType] {
    override def reads(json: JsValue) = json match {
      case s: JsString => JsSuccess(EventType.fromString(s.value))
      case x => JsError(x.toString)
    }
  }
  implicit val eventTypeWrites = new Writes[EventType] {
    override def writes(et: EventType) = JsString(et.toString)
  }

  implicit val analyticsEventReads = Json.reads[AnalyticsEvent]
  implicit val analyticsEventWrites = Json.writes[AnalyticsEvent]

  // Analytics Dependencies
  implicit val userStatisticsGamesReads = Json.reads[UserStatistics.Games]
  implicit val userStatisticsReads = Json.reads[UserStatistics]
  implicit val gameResultReads = Json.reads[GameResult]
  implicit val gameLostReads = Json.reads[GameLost]
  implicit val gameWonReads = Json.reads[GameWon]

  // Analytics Readers
  implicit val errorEventReads = Json.reads[ErrorEvent]
  implicit val installEventReads = Json.reads[InstallEvent]
  implicit val openEventReads = Json.reads[OpenEvent]
  implicit val gameStartEventReads = Json.reads[GameStartEvent]
  implicit val gameWonEventReads = Json.reads[GameWonEvent]
  implicit val gameResignedEventReads = Json.reads[GameResignedEvent]
}

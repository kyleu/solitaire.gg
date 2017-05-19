package services.audit.data

import java.util.UUID

import models.audit.AnalyticsEvent
import models.queries.user.UserQueries
import models.user.User
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import services.database.Database
import services.user.UserService
import utils.DateUtils

import scala.concurrent.Future

object AnalyticsDataInsert {
  def process(event: AnalyticsEvent) = {
    val data = clean(event.data)
    event.eventType match {
      case AnalyticsEvent.EventType.Error => ErrorHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.Install => InstallHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.Open => OpenHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.GameStart => GameHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.GameWon => GameWonHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.GameResigned => GameResignedHistoryInsert.insert(event.id, data)
      case _: AnalyticsEvent.EventType.Unknown => throw new IllegalStateException(s"Unhandled event [$event].")
    }
  }

  def test(event: AnalyticsEvent) = {
    val data = clean(event.data)
    Future.successful(event.eventType match {
      case AnalyticsEvent.EventType.Error => ErrorHistoryInsert.test(event.id, data)
      case AnalyticsEvent.EventType.Install => InstallHistoryInsert.test(event.id, data)
      case AnalyticsEvent.EventType.Open => OpenHistoryInsert.test(event.id, data)
      case AnalyticsEvent.EventType.GameStart => GameHistoryInsert.test(event.id, data)
      case AnalyticsEvent.EventType.GameWon => GameWonHistoryInsert.test(event.id, data)
      case AnalyticsEvent.EventType.GameResigned => GameResignedHistoryInsert.test(event.id, data)
      case _: AnalyticsEvent.EventType.Unknown => throw new IllegalStateException(s"Unhandled event [$event].")
    })
  }

  @scala.annotation.tailrec
  private[this] def clean(data: JsValue): JsObject = data match {
    case o: JsObject => o
    case a: JsArray if a.value.size == 2 => clean(a.value(1))
  }

  def getDate(o: Map[String, JsValue], key: String = "occurred") = o.get(key).map {
    case s: JsString => DateUtils.fromMillis(s.value.toLong)
    case n: JsNumber => DateUtils.fromMillis(n.value.toLong)
    case x => throw new IllegalStateException(s"[$x] is not a date.")
  }

  def getDeviceInfo(o: Map[String, JsValue], key: String = "deviceInfo") = o.get(key).map {
    case o: JsObject => o.value.mapValues {
      case b: JsBoolean => b.value
      case x => throw new IllegalStateException(s"[$x] is not a boolean.")
    }.filter(_._2).keySet.toSeq
    case x => throw new IllegalStateException(s"[$x] does not contain device info.")
  }.getOrElse(Nil)

  def confirmUser(userId: UUID, created: LocalDateTime) = Database.query(UserQueries.count(userId)).flatMap {
    case 0 => UserService.save(User(id = userId, created = created)).map(_ => true)
    case _ => Future.successful(false)
  }
}

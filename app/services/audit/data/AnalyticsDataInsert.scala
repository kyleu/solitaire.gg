package services.audit.data

import java.util.UUID

import models.audit.AnalyticsEvent
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import utils.DateUtils

import scala.concurrent.Future

object AnalyticsDataInsert {
  def process(event: AnalyticsEvent) = {
    val data = clean(event.data)
    val msg = event.eventType match {
      case AnalyticsEvent.EventType.Error => errorStatement(event.id, data)
      case AnalyticsEvent.EventType.Install => InstallHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.Open => OpenHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.GameStart => GameHistoryInsert.insert(event.id, data)
      case AnalyticsEvent.EventType.GameWon => wonStatement(event.id, data)
      case AnalyticsEvent.EventType.GameResigned => resignedStatement(event.id, data)
      case u: AnalyticsEvent.EventType.Unknown => throw new IllegalStateException(s"Unhandled event [$event].")
    }
    msg.map(event.copy(data = data) -> _)
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

  private[this] def getArrayString(s: Seq[String]) = {
    s"'{ ${s.map(x => "\"" + x + "\"").mkString(", ")} }'"
  }

  private[this] def errorStatement(id: UUID, data: JsValue): Future[String] = {
    Future.successful("Error: Skipped.")
  }

  private[this] def wonStatement(id: UUID, data: JsValue): Future[String] = {
    Future.successful("GameWon: Skipped.")
  }

  private[this] def resignedStatement(id: UUID, data: JsValue): Future[String] = {
    Future.successful("GameResigned: Skipped.")
  }
}

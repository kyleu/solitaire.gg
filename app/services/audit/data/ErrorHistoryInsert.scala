package services.audit.data

import java.util.UUID

import models.user.User
import org.joda.time.LocalDateTime
import play.api.libs.json.JsValue
import utils.DateUtils

import scala.concurrent.Future

object ErrorHistoryInsert {
  def test(id: UUID, data: JsValue): String = {
    parse(id, data)
    "Error: Parsed"
  }

  def insert(id: UUID, data: JsValue): Future[String] = {
    val (_, deviceId, userId, deviceInfo, client, occurred) = parse(id, data)
    queries(id, deviceId, userId, deviceInfo, client, occurred)
  }

  private[this] def parse(id: UUID, data: JsValue) = {
    val o = data.as[Map[String, JsValue]]
    val deviceId = o.get("deviceId").map(_.as[UUID]).getOrElse(User.defaultId)
    val userId = o.get("userId").map(_.as[UUID]).getOrElse(deviceId)
    val deviceInfo = AnalyticsDataInsert.getDeviceInfo(o)
    val client = o.get("client").map(_.as[String]).getOrElse("unknown")
    val occurred = AnalyticsDataInsert.getDate(o).getOrElse(DateUtils.fromMillis(0))
    (id, deviceId, userId, deviceInfo, client, occurred)
  }

  private[this] def queries(id: UUID, deviceId: UUID, userId: UUID, deviceInfo: Seq[String], client: String, occurred: LocalDateTime): Future[String] = {
    Future.successful("Error: Inserted")
  }
}

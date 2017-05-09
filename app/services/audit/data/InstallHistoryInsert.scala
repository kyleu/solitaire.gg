package services.audit.data

import java.util.UUID

import models.history.InstallHistory
import models.queries.history.InstallHistoryQueries
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsValue
import services.database.Database
import utils.DateUtils

import scala.concurrent.Future

object InstallHistoryInsert {
  def insert(id: UUID, data: JsValue): Future[String] = {
    val o = data.as[Map[String, JsValue]]
    val deviceId = o.get("deviceId").map(_.as[UUID]).getOrElse(User.defaultId)
    val userId = o.get("userId").map(_.as[UUID]).getOrElse(deviceId)
    val deviceInfo = AnalyticsDataInsert.getDeviceInfo(o)
    val client = o.get("client").map(_.as[String]).getOrElse("unknown")
    val occurred = AnalyticsDataInsert.getDate(o).getOrElse(DateUtils.fromMillis(0))
    val q = InstallHistoryQueries.insert(InstallHistory(id, userId, deviceId, deviceInfo, client, occurred))
    Database.execute(q).map(_ => "Install: Inserted")
  }
}

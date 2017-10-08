package models.queries.history

import java.util.UUID

import models.database.Row
import models.history.OpenHistory
import models.queries.BaseQueries
import java.time.LocalDateTime

object OpenHistoryQueries extends BaseQueries[OpenHistory] {
  override protected val tableName = "opens"
  override protected val columns = Seq("id", "user_id", "device_id", "device_info", "client", "occurred")
  override protected val searchColumns = Seq("id::text", "user_id::text", "device_id::text")

  def getById(id: UUID) = getBySingleId(id)
  val insert = Insert
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search
  val removeById = RemoveById
  val truncate = Truncate

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val userId = row.as[UUID]("user_id")
    val deviceId = row.as[UUID]("device_id")
    val deviceInfo = row.as[Array[String]]("device_info")
    val client = row.as[String]("client")
    val occurred = row.as[LocalDateTime]("occurred")
    OpenHistory(id, userId, deviceId, deviceInfo, client, occurred)
  }

  override protected def toDataSeq(oh: OpenHistory) = Seq[Any](
    oh.id, oh.userId, oh.deviceId, oh.deviceInfo.toArray, oh.client, oh.occurred
  )
}

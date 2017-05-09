package models.queries.history

import java.util.UUID

import models.database.Row
import models.history.InstallHistory
import models.queries.BaseQueries
import org.joda.time.LocalDateTime

object InstallHistoryQueries extends BaseQueries[InstallHistory] {
  override protected val tableName = "installs"
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
    InstallHistory(id, userId, deviceId, deviceInfo, client, occurred)
  }

  override protected def toDataSeq(ih: InstallHistory) = Seq[Any](
    ih.id, ih.userId, ih.deviceId, ih.deviceInfo.toArray, ih.client, ih.occurred
  )
}

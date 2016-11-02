package models.queries.audit

import java.util.UUID

import models.audit.ClientTraceResult
import models.database.Row
import models.queries.BaseQueries
import org.joda.time.LocalDateTime
import play.api.libs.json.{JsObject, Json}

object ClientTraceQueries extends BaseQueries[ClientTraceResult] {
  override protected val tableName = "client_trace"
  override protected val columns = Seq("id", "trace_type", "player", "data", "created")
  override protected val searchColumns = Seq("id::text", "trace_type", "player::text", "data::text")

  def getById(id: UUID) = getBySingleId(id)
  val insert = Insert
  val removeById = RemoveById
  val search = Search
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val traceType = row.as[String]("trace_type")
    val player = row.as[UUID]("player")
    val data = Json.parse(row.as[String]("data")).as[JsObject]
    val created = row.as[LocalDateTime]("created")
    ClientTraceResult(id, traceType, player, data, created)
  }

  override protected def toDataSeq(ct: ClientTraceResult) = Seq(ct.id, ct.traceType, ct.player, Json.prettyPrint(ct.data), ct.created)
}

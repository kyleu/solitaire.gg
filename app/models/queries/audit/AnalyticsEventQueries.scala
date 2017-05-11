package models.queries.audit

import java.util.UUID

import models.audit.AnalyticsEvent
import models.database.{Query, Row}
import models.queries.BaseQueries
import org.joda.time.{LocalDate, LocalDateTime}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsObject, JsString, Json}
import utils.Logging

import scala.concurrent.Future
import scala.util.control.NonFatal

object AnalyticsEventQueries extends BaseQueries[AnalyticsEvent] {
  override protected val tableName = "analytics_events"
  override protected val columns = Seq("id", "event_type", "device", "source_address", "data", "created")
  override protected val searchColumns = Seq("id::text", "event_type", "device::text")

  def getById(id: UUID) = getBySingleId(id)
  val insert = Insert
  def removeById(id: UUID) = RemoveById(Seq(id))
  val search = Search
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)

  case object GetEarliestDay extends Query[LocalDate] {
    override val sql = s"select min(created::date) as d from $tableName"
    override def reduce(rows: Iterator[Row]) = rows.next().as[LocalDate]("d")
  }

  case class GetEvents(limit: Int = 100, offset: Int = 0, whereClause: String = "1 = 1", orderBy: String = "created asc") extends Query[Seq[AnalyticsEvent]] {
    override def sql = s"select $columnString from $tableName where $whereClause order by $orderBy limit $limit offset $offset"
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toSeq
  }

  case class ProcessEvents(
      f: AnalyticsEvent => Future[String], limit: Int = 100, offset: Int = 0, whereClause: String = "1 = 1", orderBy: String = "created asc"
  ) extends Query[Future[Int]] with Logging {
    override def sql = s"select $columnString from $tableName where $whereClause order by $orderBy limit $limit offset $offset"
    override def reduce(rows: Iterator[Row]) = rows.zipWithIndex.foldLeft(Future.successful(0)) { (x, y) =>
      x.flatMap { xc =>
        if (y._2 % 1000 == 0) {
          log.info(s"Processed [${y._2}] records...")
        }
        f(fromRow(y._1)).map(_ => y._2)
      }
    }
  }

  case object GetDateCounts extends Query[Seq[(LocalDate, Int)]] {
    override def sql = s"""
      select date_trunc('day', created) as d, count(*) as c
      from $tableName
      group by date_trunc('day', created)
      order by date_trunc('day', created) desc
    """
    override def reduce(rows: Iterator[Row]) = rows.map(r => r.as[LocalDateTime]("d").toLocalDate -> r.as[Long]("c").toInt).toSeq
  }

  case class GetByDate(d: LocalDate) extends Query[Iterator[AnalyticsEvent]] {
    override def sql = s"select $columnString from $tableName where created >= ? and created < ?"
    override def values = Seq(d, d.plusDays(1))
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow)
  }

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val eventType = AnalyticsEvent.EventType.fromString(row.as[String]("event_type"))
    val device = row.as[UUID]("device")
    val sourceAddress = row.asOpt[String]("source_address")
    val data = row.as[String]("data")
    val dataObj = try {
      Json.parse(data)
    } catch {
      case NonFatal(x) => JsObject(Seq(
        "status" -> JsString("error"),
        "message" -> JsString(x.getMessage),
        "data" -> JsString(data)
      ))
    }
    val created = row.as[LocalDateTime]("created")
    AnalyticsEvent(id, eventType, device, sourceAddress, dataObj, created)
  }

  override protected def toDataSeq(ae: AnalyticsEvent) = Seq(ae.id, ae.eventType.value, ae.device, ae.sourceAddress, Json.prettyPrint(ae.data), ae.created)
}

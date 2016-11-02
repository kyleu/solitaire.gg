package models.queries.history

import java.util.UUID

import models.queries.BaseQueries
import models.database.{Query, Row, Statement}
import models.history.RequestLog
import org.joda.time.{LocalDate, LocalDateTime}

object RequestLogQueries extends BaseQueries[RequestLog] {
  override protected val tableName = "requests"
  override protected val columns = Seq(
    "id", "remote_address", "method", "host", "secure", "path", "query_string",
    "lang", "cookie", "referrer", "user_agent", "started", "duration", "status"
  )
  override protected val searchColumns = Seq("id::text", "user_id::text", "method", "host", "path", "referrer", "user_agent")

  val insert = Insert
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search
  val count = CountWhere(None)

  case class GetCounts(col: String, whereClause: Option[String] = None) extends Query[Seq[(String, Int)]] {
    override val sql = s"select r.$col as col, count(r.id) as c from requests r ${whereClause.map("where " + _).getOrElse("")} group by r.$col order by c desc;"
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      row.asOpt[String]("col").getOrElse("null") -> row.asOpt[Long]("c").getOrElse(0L).toInt
    }.toSeq
  }

  case object GetEarliestDay extends Query[LocalDate] {
    override val sql = s"select min(started::date) as d from $tableName"
    override def reduce(rows: Iterator[Row]) = rows.next().as[LocalDate]("d")
  }

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val remoteAddress = row.as[String]("remote_address")

    val method = row.as[String]("method")
    val host = row.as[String]("host")
    val secure = row.as[Boolean]("secure")
    val path = row.as[String]("path")
    val queryString = row.as[String]("query_string")

    val lang = row.asOpt[String]("lang")
    val cookie = row.asOpt[String]("cookie")
    val referrer = row.asOpt[String]("referrer")
    val userAgent = row.asOpt[String]("user_agent")
    val started = row.as[LocalDateTime]("started")
    val duration = row.as[Int]("duration")
    val status = row.as[Int]("status")

    RequestLog(
      id, remoteAddress, method, host, secure, path, queryString,
      lang, cookie, referrer, userAgent, started, duration, status
    )
  }

  override protected def toDataSeq(r: RequestLog) = Seq[Any](
    r.id, r.remoteAddress, r.method, r.host, r.secure, r.path, r.queryString,
    r.lang, r.cookie, r.referrer, r.userAgent, r.started, r.duration, r.status
  )
}

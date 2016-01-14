package models.queries.adhoc

import java.util.UUID

import com.github.mauricio.async.db.general.ArrayRowData
import models.database.{ Row, Query, Statement }
import models.queries.BaseQueries
import org.joda.time.LocalDateTime
import utils.DateUtils

object AdHocQueries extends BaseQueries[AdHocQuery] {
  override protected val tableName = "adhoc_queries"
  override protected val columns = Seq("id", "title", "sql", "created", "updated")
  override protected val searchColumns = Seq("id::text", "title", "author::text", "sql")

  val insert = Insert
  def getById(id: UUID) = getBySingleId(id)
  val search = Search
  val removeById = RemoveById

  case class UpdateAdHocQuery(id: UUID, title: String, sqlString: String) extends Statement {
    override val sql = updateSql(Seq("author", "title", "sql", "updated"))
    override val values = Seq[Any](title, sqlString, DateUtils.now, id)
  }

  case class AdHocQueryExecute(override val sql: String, override val values: Seq[Any]) extends Query[(Seq[String], Seq[Seq[Any]])] {
    override def reduce(rows: Iterator[Row]) = {
      val rowsList = rows.toList.map(_.rowData)
      val columns = (rowsList match {
        case Nil => Seq.empty[String]
        case head :: _ => head match {
          case ard: ArrayRowData => ard.mapping.toSeq.sortBy(_._2).map(_._1)
        }
      }).map {
        case "?column?" => "--"
        case s => s
      }

      val result = rowsList.map { r =>
        r.iterator.toSeq
      }.toSeq
      columns -> result
    }
  }

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val title = row.as[String]("title")
    val sql = row.as[String]("sql")
    val created = row.as[LocalDateTime]("created")
    val updated = row.as[LocalDateTime]("updated")

    AdHocQuery(id, title, sql, created, updated)
  }

  override protected def toDataSeq(q: AdHocQuery) = {
    Seq[Any](q.id, q.title, q.sql, q.created, q.updated)
  }
}

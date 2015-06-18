package models.database.queries.adhoc

import java.util.UUID

import com.github.mauricio.async.db.RowData
import com.github.mauricio.async.db.general.ArrayRowData
import models.database.{ Query, Statement }
import models.database.queries.BaseQueries
import org.joda.time.LocalDateTime

object AdHocQueries extends BaseQueries[AdHocQuery] {
  override protected val tableName = "adhoc_queries"
  override protected val columns = Seq("id", "title", "author", "sql", "created", "updated")
  override protected val searchColumns = Seq("id::text", "title", "author::text", "sql")

  val insert = Insert
  val getById = GetById
  val search = Search
  val removeById = RemoveById

  case class UpdateAdHocQuery(id: UUID, title: String, author: UUID, sqlString: String) extends Statement {
    override val sql = updateSql(Seq("author", "title", "sql", "updated"))
    override val values = Seq[Any](author, title, sqlString, new LocalDateTime(), id)
  }

  case class AdHocQueryExecute(override val sql: String, override val values: Seq[Any]) extends Query[(Seq[String], Seq[Seq[Any]])] {
    override def reduce(rows: Iterator[RowData]) = {
      val rowsList = rows.toList
      val columns = (rowsList match {
        case Nil => Seq.empty[String]
        case head :: _ => head match {
          case ard: ArrayRowData => ard.mapping.toSeq.sortBy(_._2).map(_._1)
        }
      }).map { case "?column?" => "--"; case s => s }

      val result = rowsList.map { r =>
        r.iterator.toSeq
      }.toSeq
      columns -> result
    }
  }

  override protected def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => UUID.fromString(s) }
    val title = row("title") match { case s: String => s }
    val author = row("author") match { case s: String => UUID.fromString(s) }
    val sql = row("sql") match { case s: String => s }
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    val updated = row("updated") match { case ldt: LocalDateTime => ldt }

    AdHocQuery(id, title, author, sql, created, updated)
  }

  override protected def toDataSeq(q: AdHocQuery) = {
    Seq[Any](q.id, q.title, q.author, q.sql, q.created, q.updated)
  }
}

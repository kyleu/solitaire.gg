package models.database.queries.history

import models.database.queries.BaseQueries
import models.database.{ Query, Row }
import models.history.DataArchiveCount
import org.joda.time.{ LocalDateTime, LocalDate }

object DataArchiveQueries extends BaseQueries[DataArchiveCount] {
  override protected val tableName = "data_archive"
  override protected val columns = Seq("table_name", "day", "archived_count", "archived")
  override protected val searchColumns = Seq("table_name", "day::text")

  val insertBatch = InsertBatch
  val getAll = Search("", "day, table_name", None)

  case class GetArchiveCountsByDay(day: LocalDate) extends Query[Seq[DataArchiveCount]] {
    override val sql = getSql(Some("day = ?"), Some("table_name"))
    override val values = Seq(day)
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toSeq
  }

  case class GetArchiveTotals() extends Query[Map[String, Int]] {
    override val sql = s"select table_name, sum(archived_count) as c from $tableName group by table_name"
    override def reduce(rows: Iterator[Row]) = rows.map(row => row.as[String]("table_name") -> row.as[Int]("c")).toMap
  }

  override protected def fromRow(row: Row) = DataArchiveCount(
    table = row.as[String]("table_name"),
    day = row.as[LocalDate]("day"),
    count = row.as[Int]("archived_count"),
    archived = row.as[LocalDateTime]("archived")
  )

  override protected def toDataSeq(c: DataArchiveCount) = Seq(c.table, c.day, c.count, c.archived)
}

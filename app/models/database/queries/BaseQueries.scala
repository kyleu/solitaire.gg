package models.database.queries

import com.github.mauricio.async.db.RowData
import models.database.{ FlatSingleRowQuery, Statement, SingleRowQuery, Query }
import utils.Config

trait BaseQueries[T] {
  protected def tableName: String
  protected def idColumns = Seq("id")
  protected def columns: Seq[String]
  protected def searchColumns: Seq[String]

  protected def fromRow(row: RowData): T

  private def idWhereClause = idColumns.map(c => c + " = ?").mkString(" and ")

  protected lazy val insertSql = s"insert into $tableName (${columns.mkString(", ")}) values (${columns.map(x => "?").mkString(", ")})"

  protected def updateSql(updateColumns: Seq[String], additionalUpdates: Option[String] = None) = trim(s"""
    update $tableName set ${updateColumns.map(x => x + " = ?").mkString(", ")}${additionalUpdates.map(x => ", " + x).getOrElse("")} where $idWhereClause
  """)

  protected def countSql(whereClause: String) = s"select count(*) as c from $tableName where $whereClause"

  protected def getSql(whereClause: String, orderBy: Option[String] = None, limit: Option[Int] = None, offset: Option[Int] = None) = trim(s"""
    select ${columns.mkString(", ")} from $tableName
    where $whereClause
    ${orderBy.map(x => " order by " + x).getOrElse("")}
    ${limit.map(x => " limit " + x).getOrElse("")}
    ${offset.map(x => " offset " + x).getOrElse("")}
  """)

  protected def trim(s: String) = s.replaceAll("""[\s]+""", " ").trim

  case class GetById(override val values: Seq[Any]) extends FlatSingleRowQuery[T] {
    override val sql = s"select ${columns.mkString(", ")} from $tableName where $idWhereClause"
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class RemoveById(override val values: Seq[Any]) extends Statement {
    override val sql = s"delete from $tableName where $idWhereClause"
  }

  case class CountQuery(q: String) extends Query[Int] {
    override val sql = countSql(if (q.isEmpty) { "1 = 1" } else { searchColumns.map(c => "lower(" + c + ") like lower(?)").mkString(" or ") })
    override val values = if (q.isEmpty) { Seq.empty } else { searchColumns.map(c => "%" + q + "%") }
    override def reduce(rows: Iterator[RowData]) = rows.next()("c") match { case l: Long => l.toInt }
  }

  case class SearchQuery(q: String, sortBy: String, page: Option[Int]) extends Query[List[T]] {
    private[this] val whereClause = if (q.isEmpty) { "1 = 1" } else { searchColumns.map(c => "lower(" + c + ") like lower(?)").mkString(" or ") }
    private[this] val limit = page.map(x => Config.pageSize)
    private[this] val offset = page.map(x => x * Config.pageSize)
    override val sql = getSql(whereClause, Some(sortBy), limit, offset)
    override val values = if (q.isEmpty) { Seq.empty } else { searchColumns.map(c => "%" + q + "%") }
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }
}

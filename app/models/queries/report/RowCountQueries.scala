package models.queries.report

import models.queries.BaseQueries
import models.database.{ Query, Row, SingleRowQuery }

object RowCountQueries {
  case object ListTables extends Query[List[String]] {
    override def sql = BaseQueries.trim("""
      select t.table_name as tn
      from information_schema.tables as t
      where table_catalog = 'solitaire' and table_schema = 'public' and table_type = 'BASE TABLE'
      order by table_name
    """)
    override def reduce(rows: Iterator[Row]) = rows.map(row => row.as[String]("tn")).toList
  }

  case class CountTable(t: String) extends SingleRowQuery[(String, Long)] {
    override def sql = s"select count(*) as c from $t"
    override def map(row: Row) = t -> row.as[Long]("c")
  }
}

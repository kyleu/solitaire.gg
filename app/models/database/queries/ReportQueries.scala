package models.database.queries

import com.github.mauricio.async.db.RowData
import models.database.{ SingleRowQuery, Query }

object ReportQueries {
  case object ListTables extends Query[List[String]] {
    override def sql = BaseQueries.trim("""
      select t.table_name as tn
      from information_schema.tables as t
      where table_catalog = 'solitaire' and table_schema = 'public' and table_type = 'BASE TABLE'
      order by table_name
    """)
    override def reduce(rows: Iterator[RowData]) = rows.map(row => row("tn") match { case s: String => s }).toList
  }

  case class CountTable(t: String) extends SingleRowQuery[(String, Long)] {
    override def sql = "select count(*) as c from " + t
    override def map(row: RowData) = row("c") match { case l: Long => t -> l }
  }
}

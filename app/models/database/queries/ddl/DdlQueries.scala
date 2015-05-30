package models.database.queries.ddl

import com.github.mauricio.async.db.RowData
import models.database.{ Statement, SingleRowQuery }

object DdlQueries {
  case class DoesTableExist(tableName: String) extends SingleRowQuery[Boolean] {
    override val sql = "select exists (select * from information_schema.tables WHERE table_name = ?);"
    override val values = tableName :: Nil
    override def map(row: RowData) = row("exists") match { case b: Boolean => b }
  }

  case class TruncateTables(tableNames: Seq[String]) extends Statement {
    override val sql = "truncate " + tableNames.mkString(", ")
  }

  def trim(s: String) = s.replaceAll("""[\s]+""", " ").trim
}

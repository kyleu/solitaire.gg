package models.database

import com.github.mauricio.async.db.{ ResultSet, RowData }
import services.database.Database

import scala.concurrent.Future

trait RawQuery[A] {
  def sql: String
  def values: Seq[Any] = Seq.empty
  def handle(results: ResultSet): A
  def apply(): Future[A] = Database.query(this)
}

trait Query[A] extends RawQuery[A] {
  def handle(results: ResultSet) = reduce(results.toIterator)
  def reduce(rows: Iterator[RowData]): A
}

trait SingleRowQuery[A] extends Query[A] {
  def map(row: RowData): A
  override final def reduce(rows: Iterator[RowData]) = rows.map(map).next()
}

trait FlatSingleRowQuery[A] extends Query[Option[A]] {
  def flatMap(row: RowData): Option[A]
  override final def reduce(rows: Iterator[RowData]) = if (rows.hasNext) { flatMap(rows.next()) } else { None }
}

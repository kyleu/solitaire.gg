package models.database.queries

import models.audit.DailyMetric
import models.database.{ FlatSingleRowQuery, SingleRowQuery, Statement, Row, Query }
import org.joda.time.{ LocalDateTime, LocalDate }

object DailyMetricQueries extends BaseQueries[DailyMetric] {
  override protected val tableName = "daily_metrics"
  override protected val columns = Seq("day", "metric", "value", "measured")
  override protected val idColumns = Seq("day", "metric")
  override protected val searchColumns = Seq("day", "metric", "value")

  case class GetValue(d: LocalDate, m: DailyMetric.Metric) extends FlatSingleRowQuery[Long] {
    override val sql = s"select value from $tableName where day = ? and metric = ?"
    override def values = Seq(d, m)
    override def flatMap(row: Row) = Some(row.as[Long]("value"))
  }
  val insert = Insert
  val insertBatch = InsertBatch

  case class GetMetrics(d: LocalDate) extends Query[Map[DailyMetric.Metric, Long]] {
    override def sql = s"select metric, value from $tableName where day = ?"
    override def values = Seq(d)
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      val metric = DailyMetric.fromString(row.as[String]("metric"))
      val value = row.as[Long]("value")
      metric -> value
    }.toMap
  }

  case class GetMetricHistory(metric: DailyMetric.Metric) extends Query[Seq[(LocalDate, Long)]] {
    override def sql = s"select day, value from $tableName where metric = ? order by day"
    override def values = Seq(metric)
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      val day = row.as[LocalDate]("day")
      val value = row.as[Long]("value")
      day -> value
    }.toSeq
  }

  case class UpdateMetric(dm: DailyMetric) extends Statement {
    override def sql: String = updateSql(Seq("value", "measured"))
    override def values = Seq(dm.value, dm.measured, dm.date, dm.metric)
  }

  case class CalculateMetric(metric: DailyMetric.Metric, override val sql: String, d: LocalDate) extends SingleRowQuery[(DailyMetric.Metric, Long)] {
    override val values = sql.count(_ == '?') match {
      case 0 => Nil
      case 1 => Seq(d.plusDays(1).toString + " 00:00:00")
      case 2 => Seq(d.toString + " 00:00:00", d.plusDays(1).toString + " 00:00:00")
    }
    override def map(row: Row) = metric -> row.as[Long]("c")
  }

  override protected def fromRow(row: Row) = {
    val day = row.as[LocalDate]("day")
    val metric = DailyMetric.fromString(row.as[String]("metric"))
    val value = row.as[Long]("value")
    val measured = row.as[LocalDateTime]("measured")
    DailyMetric(day, metric, value, measured)
  }

  override protected def toDataSeq(m: DailyMetric) = Seq(m.date, m.metric, m.value, m.measured)
}

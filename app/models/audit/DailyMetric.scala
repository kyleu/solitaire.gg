package models.audit

import org.joda.time.{LocalDateTime, LocalDate}

object DailyMetric {
  val all = Metric.values

  private[this] val allMap = all.map(m => m.toString -> m).toMap
  def fromString(s: String) = allMap.getOrElse(s, Metric.Unknown)
}

case class DailyMetric(date: LocalDate, metric: Metric, value: Long, measured: LocalDateTime)

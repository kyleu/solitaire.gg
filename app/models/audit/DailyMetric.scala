package models.audit

import org.joda.time.{LocalDateTime, LocalDate}

case class DailyMetric(date: LocalDate, metric: Metric, value: Long, measured: LocalDateTime)

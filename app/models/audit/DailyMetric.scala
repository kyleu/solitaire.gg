package models.audit

import java.time.{LocalDateTime, LocalDate}

case class DailyMetric(date: LocalDate, metric: Metric, value: Long, measured: LocalDateTime)

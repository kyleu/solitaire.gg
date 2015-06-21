package models.audit

import org.joda.time.{ LocalDateTime, LocalDate }

object DailyMetric {
  sealed trait Metric

  case object GamesAdandoned extends Metric
  case object GamesStarted extends Metric
  case object GamesWon extends Metric

  case object Signups extends Metric

  case object Requests extends Metric

  case object ReportSent extends Metric

  val all = Seq[Metric](
    GamesAdandoned, GamesStarted, GamesWon,
    Signups,
    Requests,
    ReportSent
  )

  private[this] val allMap = all.map(m => m.toString -> m).toMap
  def fromString(s: String) = allMap(s)
}

case class DailyMetric(date: LocalDate, metric: DailyMetric.Metric, value: Long, measured: LocalDateTime)

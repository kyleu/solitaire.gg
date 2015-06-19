package models.audit

import org.joda.time.{ LocalDateTime, LocalDate }

object DailyMetric {
  sealed trait Metric

  case object GamesAdandoned extends Metric
  case object GamesStarted extends Metric
  case object GamesWon extends Metric

  case object Requests extends Metric
  case object Signups extends Metric

  case object TotalUsers extends Metric
  case object TotalGames extends Metric
  case object TotalRequests extends Metric

  case object ReportSent extends Metric

  val all = Seq[Metric](
    GamesAdandoned, GamesStarted, GamesWon,
    Requests, Signups,
    TotalUsers, TotalGames, TotalRequests,
    ReportSent
  )

  def fromString(s: String) = all.find(_.toString == s).getOrElse(throw new IllegalStateException(s"Unknown metric [$s]."))
}

case class DailyMetric(date: LocalDate, metric: DailyMetric.Metric, value: Long, measured: LocalDateTime)

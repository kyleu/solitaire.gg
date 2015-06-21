package models.audit

import org.joda.time.{ LocalDateTime, LocalDate }

object DailyMetric {
  sealed trait Metric {
    def title: String
  }

  case object GamesStarted extends Metric {
    override val title = "Games"
  }
  case object GamesWon extends Metric {
    override val title = "Won"
  }
  case object GamesAdandoned extends Metric {
    override val title = "Abandoned"
  }

  case object Signups extends Metric {
    override val title = "Signups"
  }

  case object Requests extends Metric {
    override val title = "Requests"
  }

  case object Feedbacks extends Metric {
    override val title = "Feedbacks"
  }

  case object ReportSent extends Metric {
    override val title = "Mailed"
  }

  val all = Seq[Metric](
    GamesStarted, GamesWon, GamesAdandoned,
    Signups,
    Requests,
    Feedbacks,
    ReportSent
  )

  private[this] val allMap = all.map(m => m.toString -> m).toMap
  def fromString(s: String) = allMap(s)
}

case class DailyMetric(date: LocalDate, metric: DailyMetric.Metric, value: Long, measured: LocalDateTime)

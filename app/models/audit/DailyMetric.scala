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

  case object OfflineGamesStarted extends Metric {
    override val title = "OfflineGames"
  }
  case object OfflineGamesWon extends Metric {
    override val title = "OfflineWon"
  }
  case object OfflineGamesAdandoned extends Metric {
    override val title = "OfflineAbandoned"
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

  case object ServerFreeSpace extends Metric {
    override val title = "Server Free Space"
  }

  case object ReportSent extends Metric {
    override val title = "Mailed"
  }

  val all = Seq[Metric](
    GamesStarted, GamesWon, GamesAdandoned,
    OfflineGamesStarted, OfflineGamesWon, OfflineGamesAdandoned,
    Signups, Requests, Feedbacks,
    ServerFreeSpace,
    ReportSent
  )

  private[this] val allMap = all.map(m => m.toString -> m).toMap
  def fromString(s: String) = allMap(s)
}

case class DailyMetric(date: LocalDate, metric: DailyMetric.Metric, value: Long, measured: LocalDateTime)

package models.audit

import enumeratum._

sealed abstract class Metric(val title: String) extends EnumEntry

object Metric extends Enum[Metric] {
  case object GamesStarted extends Metric("Games")
  case object GamesWon extends Metric("Won")
  case object GamesAdandoned extends Metric("Abandoned")
  case object Signups extends Metric("Signups")
  case object Feedbacks extends Metric("Feedbacks")
  case object ServerFreeSpace extends Metric("Server Free Space")
  case object ReportSent extends Metric("Mailed")
  case object Unknown extends Metric("Unknown")

  override val values = findValues
}

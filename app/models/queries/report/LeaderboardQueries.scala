package models.queries.report

import java.util.UUID

import enumeratum._
import models.database.{Query, Row}

sealed abstract class LeaderboardQueries extends EnumEntry with Query[Seq[(UUID, Option[String], Option[String], Int)]] {
  override def reduce(rows: Iterator[Row]) = rows.map { row =>
    (row.as[UUID]("id"), row.asOpt[String]("username"), row.asOpt[String]("email"), row.as[Int]("v"))
  }.toSeq
}

object LeaderboardQueries extends Enum[LeaderboardQueries] {
  val numRows = 25

  sealed abstract class StatsQuery(v: String) extends LeaderboardQueries {
    override def sql = s"select u.id, u.username, u.email, s.$v as v from user_statistics s join users u on s.id = u.id order by s.$v desc limit $numRows"
  }

  case object GamesPlayed extends StatsQuery("played")
  case object Wins extends StatsQuery("wins")
  case object Losses extends StatsQuery("losses")
  case object MaxWinStreak extends StatsQuery("max_win_streak")

  override val values = findValues
}

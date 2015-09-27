package models.user

import java.util.UUID

object UserStatistics {
  object Games {
    lazy val empty = Games(0, 0, 0L, 0, 0, 0, None, None, 0, 0, 0, 0)
  }

  case class Games(
      wins: Int,
      losses: Int,
      totalDurationMs: Long,
      totalMoves: Int,
      totalUndos: Int,
      totalRedos: Int,
      lastWin: Option[Long],
      lastLoss: Option[Long],
      currentWinStreak: Int,
      maxWinStreak: Int,
      currentLossStreak: Int,
      maxLossStreak: Int
  ) {
    val totalPlayed = wins + losses
    val avgDuration = if (totalPlayed == 0) { 0 } else { totalDurationMs / totalPlayed }
    val avgMoves = if (totalPlayed == 0) { 0 } else { totalMoves / totalPlayed }
  }
}

case class UserStatistics(
  userId: UUID,
  joined: Long,
  games: UserStatistics.Games
)

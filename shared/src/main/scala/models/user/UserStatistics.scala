package models.user

import java.util.UUID

object UserStatistics {
  case class Games(
      wins: Int = 0,
      losses: Int = 0,
      totalDurationMs: Long = 0L,
      totalMoves: Int = 0,
      totalUndos: Int = 0,
      totalRedos: Int = 0,
      lastWin: Option[Long] = None,
      lastLoss: Option[Long] = None,
      currentWinStreak: Int = 0,
      maxWinStreak: Int = 0,
      currentLossStreak: Int = 0,
      maxLossStreak: Int = 0
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

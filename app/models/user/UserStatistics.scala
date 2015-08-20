package models.user

import java.util.UUID

import org.joda.time.{ LocalDateTime, LocalDate }

object UserStatistics {
  case class Games(
      wins: Int = 0,
      losses: Int = 0,
      totalDurationMs: Long = 0L,
      totalMoves: Int = 0,
      totalUndos: Int = 0,
      totalRedos: Int = 0,
      lastWin: Option[LocalDateTime] = None,
      lastLoss: Option[LocalDateTime] = None,
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
  joined: LocalDate,
  games: UserStatistics.Games
)

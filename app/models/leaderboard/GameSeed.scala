package models.leaderboard

import java.util.UUID

import org.joda.time.LocalDateTime

case class GameSeed(
  rules: String,
  seed: Int,
  player: UUID,
  moves: Int,
  elapsed: Int,
  completed: LocalDateTime
)

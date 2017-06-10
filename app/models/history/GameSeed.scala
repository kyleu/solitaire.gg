package models.history

import java.util.UUID

case class GameSeed(
  rules: String,
  seed: Int,
  games: Int = 0,
  wins: Int = 0,
  player: Option[UUID] = None,
  moves: Int = 0,
  elapsedMs: Int = 0,
  completed: Option[Long] = None
)

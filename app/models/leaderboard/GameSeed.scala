package models.leaderboard

import java.util.UUID

import org.joda.time.LocalDateTime

object GameSeed {
  case class SeedCount(
    seeds: Int,
    games: Int,
    wins: Int,
    minMoves: Int,
    avgMoves: Int,
    maxMoves: Int
  )

  val emptyCount = SeedCount(0, 0, 0, 0, 0, 0)
}

case class GameSeed(
  rules: String,
  seed: Int,
  games: Int,
  wins: Int,
  player: Option[UUID],
  moves: Option[Int],
  elapsed: Option[Int],
  completed: Option[LocalDateTime]
)

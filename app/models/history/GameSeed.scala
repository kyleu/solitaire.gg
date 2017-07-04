package models.history

import java.util.UUID

import org.joda.time.LocalDateTime

object GameSeed {
  case class Record(player: UUID, moves: Int, elapsed: Int, occurred: LocalDateTime)
}

case class GameSeed(
  rules: String,
  seed: Int,
  games: Int = 0,
  wins: Int = 0,
  moves: Int = 0,
  first: Option[GameSeed.Record] = None,
  fastest: Option[GameSeed.Record] = None
)

package models

import java.util.UUID

import models.game.{Suit, Rank}
import org.joda.time.LocalDateTime

object GameHistory {
  case class Card(
    id: UUID,
    gameId: UUID,
    sortOrder: Int,
    rank: Rank,
    suit: Suit
  )

  case class Move(
    gameId: UUID,
    sequence: Int,
    playerId: UUID,
    moveType: String,
    cards: Seq[UUID],
    src: String,
    tgt: String,
    occurred: LocalDateTime
  )
}

case class GameHistory(
  id: UUID,
  seed: Int,
  rules: String,
  status: String,
  player: UUID,
  moves: Int,
  undos: Int,
  redos: Int,
  created: LocalDateTime,
  completed: Option[LocalDateTime]
)

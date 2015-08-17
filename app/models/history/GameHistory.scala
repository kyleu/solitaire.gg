package models.history

import java.util.UUID

import models.card.{ Rank, Suit }
import org.joda.time.LocalDateTime
import utils.DateUtils

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
    cards: Option[Seq[UUID]] = None,
    src: Option[String] = None,
    tgt: Option[String] = None,
    occurred: LocalDateTime = DateUtils.now
  )
}

case class GameHistory(
  id: UUID,
  seed: Int,
  rules: String,
  status: String,
  player: UUID,
  cards: Int,
  moves: Int,
  undos: Int,
  redos: Int,
  created: LocalDateTime,
  completed: Option[LocalDateTime]
) {
  val duration = {
    val createdMillis = DateUtils.toMillis(created)
    val completedMillis = completed match {
      case Some(t) => DateUtils.toMillis(t)
      case None => DateUtils.nowMillis
    }
    completedMillis - createdMillis
  }
  val isWin = status == "win"
  val isCompleted = completed.isDefined
}

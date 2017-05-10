package models.history

import java.util.UUID

import models.card.{Rank, Suit}
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
    rules: String,
    seed: Int,
    status: String,
    player: UUID,
    cards: Int,
    moves: Int,
    undos: Int,
    redos: Int,
    score: Int,
    created: LocalDateTime,
    firstMove: Option[LocalDateTime],
    completed: Option[LocalDateTime],
    logged: Option[LocalDateTime]
) {
  lazy val duration = {
    val createdMillis = DateUtils.toMillis(created)
    val completedMillis = completed match {
      case Some(t) => DateUtils.toMillis(t)
      case None => DateUtils.nowMillis
    }
    completedMillis - createdMillis
  }
  val isWin = status == "win"
  val isCompleted = completed.isDefined
  val isLogged = logged.isDefined
}

package models.history

import java.util.UUID

import enumeratum.values._
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

  sealed abstract class Status(val value: String) extends StringEnumEntry

  object Status extends StringEnum[Status] with StringCirceEnum[Status] {
    case object Started extends Status("s")
    case object Won extends Status("w")
    case object Lost extends Status("l")
    case object Resigned extends Status("r")

    override val values = findValues
  }
}

case class GameHistory(
    id: UUID,
    rules: String,
    seed: Int,
    status: GameHistory.Status,
    player: UUID,
    cards: Int = 0,
    moves: Int = 0,
    undos: Int = 0,
    redos: Int = 0,
    score: Int = 0,
    created: LocalDateTime = DateUtils.now,
    firstMove: Option[LocalDateTime] = None,
    completed: Option[LocalDateTime] = None
) {
  lazy val duration = {
    val createdMillis = DateUtils.toMillis(created)
    val completedMillis = completed match {
      case Some(t) => DateUtils.toMillis(t)
      case None => DateUtils.nowMillis
    }
    completedMillis - createdMillis
  }
  val isWon = status == GameHistory.Status.Won
}

package models

import java.util.UUID

import models.game.{ Card, GameState }

sealed trait ResponseMessage
trait ReversibleResponseMessage extends ResponseMessage

case class ServerError(reason: String, content: String) extends ResponseMessage
case class Pong(timestamp: Long) extends ResponseMessage
case class VersionResponse(version: String) extends ResponseMessage
case object SendDebugInfo extends ResponseMessage
case class Disconnected(reason: String) extends ResponseMessage

case class GameJoined(id: UUID, state: GameState, moves: Seq[PossibleMove]) extends ResponseMessage
case class GameLost(id: UUID) extends ResponseMessage
case class GameWon(id: UUID) extends ResponseMessage

case class PossibleMove(moveType: String, cards: Seq[UUID], sourcePile: String, targetPile: Option[String] = None)
case class PossibleMoves(moves: Seq[PossibleMove]) extends ResponseMessage

case class CardRevealed(card: Card) extends ReversibleResponseMessage
case class CardRemoved(card: UUID) extends ReversibleResponseMessage

case class CardMoveCancelled(cards: Seq[UUID], source: String) extends ResponseMessage
case class CardMoved(
  card: UUID, source: String, target: String, targetIndex: Option[Int] = None, turnFaceUp: Boolean = false, turnFaceDown: Boolean = false
) extends ReversibleResponseMessage

case class MessageSet(messages: Seq[ResponseMessage]) extends ReversibleResponseMessage {
  def reverse = MessageSet(messages.flatMap {
    case rrm: ReversibleResponseMessage => Some(rrm)
    case _ => None
  }.reverse)
}

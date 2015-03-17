package models

import java.util.UUID

import play.api.libs.json.JsObject

sealed trait RequestMessage

case class MalformedRequest(reason: String, content: String) extends RequestMessage
case class Ping(timestamp: Long) extends RequestMessage
case object GetVersion extends RequestMessage
case class DebugInfo(data: JsObject) extends RequestMessage

case class StartGame(variant: String, seed: Option[Int] = None) extends RequestMessage
case class JoinGame(id: UUID) extends RequestMessage
case class ObserveGame(id: UUID, as: Option[String]) extends RequestMessage

trait GameMessage extends RequestMessage

case object GetPossibleMoves extends GameMessage
case class SelectCard(card: UUID, pile: String, pileIndex: Int) extends GameMessage
case class SelectPile(pile: String) extends GameMessage
case class MoveCards(cards: Seq[UUID], src: String, tgt: String) extends GameMessage

case object Undo extends GameMessage

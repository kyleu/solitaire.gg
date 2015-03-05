package models

sealed trait RequestMessage

case class MalformedRequest(reason: String, content: String) extends RequestMessage
case class Ping(timestamp: Long) extends RequestMessage
case object GetVersion extends RequestMessage
case class JoinGame(game: String) extends RequestMessage

trait GameMessage extends RequestMessage

case class SelectCard(card: String, pile: String, pileIndex: Int) extends GameMessage
case class SelectPile(pile: String) extends GameMessage
case class MoveCards(cards: List[String], src: String, tgt: String) extends GameMessage

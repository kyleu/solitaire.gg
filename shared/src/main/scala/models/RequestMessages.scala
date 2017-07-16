package models

sealed trait RequestMessage

case class MalformedRequest(reason: String, content: String) extends RequestMessage

sealed trait GameMessage extends RequestMessage

case object RefreshGameState extends GameMessage
case object GetPossibleMoves extends GameMessage

// Select Card
case class SC(card: Int, pile: String, auto: Boolean) extends GameMessage

// Select Pile
case class SP(pile: String, auto: Boolean) extends GameMessage

// Move Cards
case class MC(cards: Seq[Int], src: String, tgt: String, auto: Boolean) extends GameMessage

// Undo
case object UN extends GameMessage

// Redo
case object RE extends GameMessage

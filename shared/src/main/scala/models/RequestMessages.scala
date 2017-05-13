package models

sealed trait RequestMessage

case class MalformedRequest(reason: String, content: String) extends RequestMessage

sealed trait GameMessage extends RequestMessage

case object GetPossibleMoves extends GameMessage

case class SelectCard(card: Int, pile: String, auto: Boolean) extends GameMessage
case class SelectPile(pile: String, auto: Boolean) extends GameMessage
case class MoveCards(cards: Seq[Int], src: String, tgt: String, auto: Boolean) extends GameMessage

case object RefreshGameState extends GameMessage

case object Undo extends GameMessage
case object Redo extends GameMessage


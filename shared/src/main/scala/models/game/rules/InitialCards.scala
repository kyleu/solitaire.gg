package models.game.rules

sealed trait InitialCards
object InitialCards {
  case class Count(n: Int) extends InitialCards
  case object PileIndex extends InitialCards
  case object RestOfDeck extends InitialCards
  case object Custom extends InitialCards
}

package models.rules

import enumeratum._

sealed trait InitialCards extends EnumEntry

object InitialCards extends Enum[InitialCards] {
  case class Count(n: Int) extends InitialCards
  case object PileIndex extends InitialCards
  case object RestOfDeck extends InitialCards
  case object Custom extends InitialCards

  override val values = findValues
}

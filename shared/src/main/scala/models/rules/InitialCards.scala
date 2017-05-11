package models.rules

import enumeratum.values._

sealed abstract class InitialCards(val value: String) extends StringEnumEntry

object InitialCards extends StringEnum[InitialCards] with StringUPickleEnum[InitialCards] {
  case class Count(n: Int) extends InitialCards("count")
  case object PileIndex extends InitialCards("pile-index")
  case object RestOfDeck extends InitialCards("rest-of-deck")
  case object Custom extends InitialCards("custom")

  override val values = findValues
}

package models.rules

import enumeratum.values._

sealed abstract class InitialCards(val value: Int) extends IntEnumEntry

object InitialCards extends IntEnum[InitialCards] with IntUPickleEnum[InitialCards] {
  case object PileIndex extends InitialCards(1)
  case object RestOfDeck extends InitialCards(2)
  case object Custom extends InitialCards(3)
  case class Count(n: Int) extends InitialCards(4)

  override val values = findValues
}

package models.settings

import enumeratum.values._

sealed abstract class CardRanks(val value: String) extends StringEnumEntry {
  override def toString = value
}

object CardRanks extends StringEnum[CardRanks] with StringUPickleEnum[CardRanks] {
  case object A extends CardRanks("a")
  case object B extends CardRanks("b")
  case object C extends CardRanks("c")

  override val values = findValues
}

package models.settings

import enumeratum.values._

sealed abstract class CardRanks(val value: String, val title: String) extends StringEnumEntry {
  override def toString = value
}

object CardRanks extends StringEnum[CardRanks] with StringCirceEnum[CardRanks] {
  case object A extends CardRanks("a", "Ranks A")
  case object B extends CardRanks("b", "Ranks B")

  override val values = findValues
}

package models.settings

import enumeratum.values._

sealed abstract class CardSuits(val value: String, val title: String) extends StringEnumEntry {
  override def toString = value
}

object CardSuits extends StringEnum[CardSuits] with StringCirceEnum[CardSuits] {
  case object A extends CardSuits("a", "Suits A")
  case object B extends CardSuits("b", "Suits B")
  case object C extends CardSuits("c", "Suits C")

  override val values = findValues
}

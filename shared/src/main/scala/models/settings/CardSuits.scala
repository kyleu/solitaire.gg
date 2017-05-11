package models.settings

import enumeratum.values._

sealed abstract class CardSuits(val value: String) extends StringEnumEntry {
  override def toString = value
}

object CardSuits extends StringEnum[CardSuits] with StringUPickleEnum[CardSuits] {
  case object A extends CardSuits("a")
  case object B extends CardSuits("b")
  case object C extends CardSuits("c")

  override val values = findValues
}

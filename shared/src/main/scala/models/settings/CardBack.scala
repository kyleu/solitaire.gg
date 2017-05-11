package models.settings

import enumeratum.values._

sealed abstract class CardBack(val value: String) extends StringEnumEntry {
  override def toString = value
}

object CardBack extends StringEnum[CardBack] with StringUPickleEnum[CardBack] {
  case object A extends CardBack("a")
  case object B extends CardBack("b")
  case object C extends CardBack("c")

  override val values = findValues
}

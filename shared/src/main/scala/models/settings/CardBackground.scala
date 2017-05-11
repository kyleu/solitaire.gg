package models.settings

import enumeratum.values._

sealed abstract class CardBackground(val value: String) extends StringEnumEntry {
  override def toString = value
}

object CardBackground extends StringEnum[CardBackground] with StringUPickleEnum[CardBackground] {
  case object A extends CardBackground("a")
  case object B extends CardBackground("b")
  case object C extends CardBackground("c")

  override val values = findValues
}

package models.settings

import enumeratum.values._

sealed abstract class CardBlank(val value: String) extends StringEnumEntry {
  override def toString = value
}

object CardBlank extends StringEnum[CardBlank] with StringUPickleEnum[CardBlank] {
  case object A extends CardBlank("a")
  case object B extends CardBlank("b")
  case object C extends CardBlank("c")

  override val values = findValues
}

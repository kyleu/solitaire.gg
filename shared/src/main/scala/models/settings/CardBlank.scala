package models.settings

import enumeratum.values._

sealed abstract class CardBlank(val value: String, val title: String) extends StringEnumEntry {
  override def toString = value
}

object CardBlank extends StringEnum[CardBlank] with StringUPickleEnum[CardBlank] {
  case object A extends CardBlank("a", "Blank A")
  case object B extends CardBlank("b", "Blank B")
  case object C extends CardBlank("c", "Blank C")

  override val values = findValues
}

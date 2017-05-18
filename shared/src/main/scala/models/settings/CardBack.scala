package models.settings

import enumeratum.values._

sealed abstract class CardBack(val value: String, val title: String) extends StringEnumEntry {
  override def toString = value
}

object CardBack extends StringEnum[CardBack] with StringCirceEnum[CardBack] {
  case object A extends CardBack("a", "Card Back A")
  case object B extends CardBack("b", "Card Back B")
  case object C extends CardBack("c", "Card Back C")

  override val values = findValues
}

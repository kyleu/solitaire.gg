package models.settings

import enumeratum.values._

sealed abstract class CardSet(val value: String, val w: Int, val h: Int, val hOffset: Int, val vOffset: Int) extends StringEnumEntry

case object CardSet extends StringEnum[CardSet] with StringUPickleEnum[CardSet] {
  case object Default extends CardSet(value = "default", w = 400, h = 600, hOffset = 80, vOffset = 120)
  case object Small extends CardSet(value = "small", w = 200, h = 300, hOffset = 40, vOffset = 60)

  override val values = findValues
}


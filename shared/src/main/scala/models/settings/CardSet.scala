package models.settings

import enumeratum.values._
import util.EnumWithDescription

sealed abstract class CardSet(
  override val value: String, override val description: String, val w: Int, val h: Int, val hOffset: Int, val vOffset: Int
) extends EnumWithDescription

object CardSet extends StringEnum[CardSet] with StringCirceEnum[CardSet] {
  case object Default extends CardSet(value = "default", description = "Default", w = 400, h = 600, hOffset = 80, vOffset = 120)

  override val values = findValues
}


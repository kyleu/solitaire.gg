package models.settings

import enumeratum.values._
import utils.EnumWithDescription

sealed abstract class CardBack(override val value: String, override val description: String) extends EnumWithDescription

object CardBack extends StringEnum[CardBack] with StringCirceEnum[CardBack] {
  case object A extends CardBack("a", "Card Back A")
  case object B extends CardBack("b", "Card Back B")
  case object C extends CardBack("c", "Card Back C")

  override val values = findValues
}

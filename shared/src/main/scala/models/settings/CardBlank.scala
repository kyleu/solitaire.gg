package models.settings

import enumeratum.values._
import utils.EnumWithDescription

sealed abstract class CardBlank(override val value: String, override val description: String) extends EnumWithDescription

object CardBlank extends StringEnum[CardBlank] with StringCirceEnum[CardBlank] {
  case object A extends CardBlank("a", "Blank A")
  case object B extends CardBlank("b", "Blank B")
  case object C extends CardBlank("c", "Blank C")

  override val values = findValues
}

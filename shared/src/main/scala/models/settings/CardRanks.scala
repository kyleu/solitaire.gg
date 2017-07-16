package models.settings

import enumeratum.values._
import util.EnumWithDescription

sealed abstract class CardRanks(override val value: String, override val description: String) extends EnumWithDescription

object CardRanks extends StringEnum[CardRanks] with StringCirceEnum[CardRanks] {
  case object A extends CardRanks("a", "Ranks A")
  case object B extends CardRanks("b", "Ranks B")

  override val values = findValues
}

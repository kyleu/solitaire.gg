package models.settings

import enumeratum.values._
import util.EnumWithDescription

sealed abstract class CardSuits(override val value: String, override val description: String) extends EnumWithDescription

object CardSuits extends StringEnum[CardSuits] with StringCirceEnum[CardSuits] {
  case object A extends CardSuits("a", "Suits A")
  case object B extends CardSuits("b", "Suits B")

  override val values = findValues
}

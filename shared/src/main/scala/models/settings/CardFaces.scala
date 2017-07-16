package models.settings

import enumeratum.values._
import util.EnumWithDescription

sealed abstract class CardFaces(override val value: String, override val description: String) extends EnumWithDescription

object CardFaces extends StringEnum[CardFaces] with StringCirceEnum[CardFaces] {
  case object A extends CardFaces("a", "Face A")
  case object B extends CardFaces("b", "Face B")

  override val values = findValues
}

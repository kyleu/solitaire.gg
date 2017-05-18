package models.settings

import enumeratum.values._

sealed abstract class CardFaces(val value: String, val title: String) extends StringEnumEntry {
  override def toString = value
}

object CardFaces extends StringEnum[CardFaces] with StringCirceEnum[CardFaces] {
  case object A extends CardFaces("a", "Face A")
  case object B extends CardFaces("b", "Face B")
  case object C extends CardFaces("c", "Face C")

  override val values = findValues
}

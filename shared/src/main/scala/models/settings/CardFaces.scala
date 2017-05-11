package models.settings

import enumeratum.values._

sealed abstract class CardFaces(val value: String) extends StringEnumEntry {
  override def toString = value
}

object CardFaces extends StringEnum[CardFaces] with StringUPickleEnum[CardFaces] {
  case object A extends CardFaces("a")
  case object B extends CardFaces("b")
  case object C extends CardFaces("c")

  override val values = findValues
}

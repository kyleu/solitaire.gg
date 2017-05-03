package models.card

import enumeratum._

sealed trait Color extends EnumEntry

object Color extends Enum[Color] {
  case object Red extends Color
  case object Black extends Color
  case object Green extends Color
  case object Blue extends Color
  case object Colorless extends Color
  case object UnknownColor extends Color

  override val values = findValues
}

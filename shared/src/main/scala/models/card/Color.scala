package models.card

import enumeratum.values._

sealed abstract class Color(val value: Char) extends CharEnumEntry

object Color extends CharEnum[Color] with CharUPickleEnum[Color] {
  case object Red extends Color('R')
  case object Black extends Color('B')
  case object Green extends Color('G')
  case object Blue extends Color('U')
  case object Colorless extends Color('X')
  case object UnknownColor extends Color('?')

  override val values = findValues
}

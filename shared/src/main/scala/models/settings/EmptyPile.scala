package models.settings

import enumeratum.values._

sealed abstract class EmptyPile(val value: String) extends StringEnumEntry {
  override def toString = value
}

object EmptyPile extends StringEnum[EmptyPile] with StringUPickleEnum[EmptyPile] {
  case object A extends EmptyPile("a")
  case object B extends EmptyPile("b")
  case object C extends EmptyPile("c")

  override val values = findValues
}

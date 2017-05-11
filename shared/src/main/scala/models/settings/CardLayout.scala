package models.settings

import enumeratum.values._

sealed abstract class CardLayout(val value: String) extends StringEnumEntry {
  override def toString = value
}

object CardLayout extends StringEnum[CardLayout] with StringUPickleEnum[CardLayout] {
  case object A extends CardLayout("a")
  case object B extends CardLayout("b")
  case object C extends CardLayout("c")

  override val values = findValues
}

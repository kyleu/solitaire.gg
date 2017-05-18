package models.settings

import enumeratum.values._

sealed abstract class CardLayout(val value: String, val title: String) extends StringEnumEntry {
  override def toString = value
}

object CardLayout extends StringEnum[CardLayout] with StringCirceEnum[CardLayout] {
  case object A extends CardLayout("a", "Layout A")
  case object B extends CardLayout("b", "Layout B")

  override val values = findValues
}

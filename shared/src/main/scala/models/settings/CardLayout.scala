package models.settings

import enumeratum.values._
import utils.EnumWithDescription

sealed abstract class CardLayout(override val value: String, override val description: String) extends EnumWithDescription

object CardLayout extends StringEnum[CardLayout] with StringCirceEnum[CardLayout] {
  case object A extends CardLayout("a", "Layout A")
  case object B extends CardLayout("b", "Layout B")

  override val values = findValues
}

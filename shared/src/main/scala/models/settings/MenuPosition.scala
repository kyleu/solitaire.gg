package models.settings

import enumeratum.values._

sealed abstract class MenuPosition(val value: String) extends StringEnumEntry

object MenuPosition extends StringEnum[MenuPosition] with StringCirceEnum[MenuPosition] {
  case object Top extends MenuPosition("top")
  case object Right extends MenuPosition("right")
  case object Bottom extends MenuPosition("bottom")
  case object Left extends MenuPosition("left")
  case object Hidden extends MenuPosition("hidden")

  override val values = findValues
}


package models.settings

import enumeratum.values._
import utils.EnumWithDescription

sealed abstract class MenuPosition(override val value: String, override val description: String) extends EnumWithDescription

object MenuPosition extends StringEnum[MenuPosition] with StringCirceEnum[MenuPosition] {
  case object Top extends MenuPosition("top", "Top")
  case object Right extends MenuPosition("right", "Right")
  case object Bottom extends MenuPosition("bottom", "Bottom")
  case object Left extends MenuPosition("left", "Left")
  case object Hidden extends MenuPosition("hidden", "Hidden")

  override val values = findValues
}


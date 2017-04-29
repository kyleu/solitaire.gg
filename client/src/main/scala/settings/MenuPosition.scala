package settings

import enumeratum._

sealed abstract class MenuPosition(val key: String) extends EnumEntry {
  override def toString = key
}

object MenuPosition extends Enum[MenuPosition] {
  case object Top extends MenuPosition("top")
  case object Right extends MenuPosition("right")
  case object Bottom extends MenuPosition("bottom")
  case object Left extends MenuPosition("left")
  case object Hidden extends MenuPosition("hidden")

  override val values = findValues
}


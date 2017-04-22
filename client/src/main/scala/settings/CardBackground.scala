package settings

import enumeratum._

sealed abstract class CardBackground(val key: String) extends EnumEntry {
  override def toString = key
}

object CardBackground {
  case object A extends CardBackground("a")
  case object B extends CardBackground("b")
  case object C extends CardBackground("c")
}

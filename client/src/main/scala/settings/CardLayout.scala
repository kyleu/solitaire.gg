package settings

import enumeratum._

sealed abstract class CardLayout(val key: String) extends EnumEntry {
  override def toString = key
}

object CardLayout {
  case object A extends CardLayout("a")
  case object B extends CardLayout("b")
  case object C extends CardLayout("c")
}

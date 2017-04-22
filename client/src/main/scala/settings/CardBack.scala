package settings

import enumeratum._

sealed abstract class CardBack(val key: String) extends EnumEntry {
  override def toString = key
}

object CardBack {
  case object A extends CardBack("a")
  case object B extends CardBack("b")
  case object C extends CardBack("c")
}

package settings

import enumeratum._

sealed abstract class CardSuits(val key: String) extends EnumEntry {
  override def toString = key
}

object CardSuits {
  case object A extends CardSuits("a")
  case object B extends CardSuits("b")
  case object C extends CardSuits("c")
}

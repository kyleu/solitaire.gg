package settings

import enumeratum._

sealed abstract class CardRanks(val key: String) extends EnumEntry {
  override def toString = key
}

object CardRanks {
  case object A extends CardRanks("a")
  case object B extends CardRanks("b")
  case object C extends CardRanks("c")
}

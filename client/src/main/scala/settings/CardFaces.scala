package settings

import enumeratum._

sealed abstract class CardFaces(val key: String) extends EnumEntry {
  override def toString = key
}

object CardFaces {
  case object A extends CardFaces("a")
  case object B extends CardFaces("b")
  case object C extends CardFaces("c")
}

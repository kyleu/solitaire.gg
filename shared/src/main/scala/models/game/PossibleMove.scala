package models.game

import enumeratum.values._

object PossibleMove {
  sealed abstract class Type(val value: String) extends StringEnumEntry

  object Type extends StringEnum[Type] {
    case object MoveCards extends Type("mc")
    case object SelectCard extends Type("sc")
    case object SelectPile extends Type("sp")

    override def values = findValues
  }
}

case class PossibleMove(t: PossibleMove.Type, cards: Seq[Int], sourcePile: String, targetPile: Option[String] = None)

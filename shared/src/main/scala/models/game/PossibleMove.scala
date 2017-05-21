package models.game

import enumeratum.values._

object PossibleMove {
  sealed abstract class Type(val value: Char) extends CharEnumEntry

  object Type extends CharEnum[Type] with CharCirceEnum[Type] {
    case object MoveCards extends Type('m')
    case object SelectCard extends Type('c')
    case object SelectPile extends Type('p')

    override def values = findValues
  }
}

case class PossibleMove(t: PossibleMove.Type, cards: Seq[Int], sourcePile: String, targetPile: Option[String] = None)

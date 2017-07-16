package models.game

import enumeratum.values._
import models.{MC, SC, SP, UN}

object PossibleMove {
  sealed abstract class Type(val value: Char) extends CharEnumEntry

  object Type extends CharEnum[Type] with CharCirceEnum[Type] {
    case object MoveCards extends Type('m')
    case object SelectCard extends Type('c')
    case object SelectPile extends Type('p')
    case object Undo extends Type('u')

    override def values = findValues
  }
}

case class PossibleMove(t: PossibleMove.Type, sourcePile: String, cards: Seq[Int] = Nil, targetPile: Option[String] = None) {
  def toMessage = t match {
    case PossibleMove.Type.MoveCards => MC(cards, sourcePile, targetPile.getOrElse(throw new IllegalStateException()), auto = true)
    case PossibleMove.Type.SelectCard => SC(cards.headOption.getOrElse(throw new IllegalStateException()), sourcePile, auto = true)
    case PossibleMove.Type.SelectPile => SP(sourcePile, auto = true)
    case PossibleMove.Type.Undo => UN
  }
}

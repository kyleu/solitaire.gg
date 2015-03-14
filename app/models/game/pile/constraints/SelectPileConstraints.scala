package models.game.pile.constraints

import models.game.pile.{PileOption, Pile}

case class SelectPileConstraint(override val id: String, f: Pile => Boolean) extends PileOption(id)

object SelectPileConstraints {
  val never = SelectPileConstraint("never", pile => false)

  val empty = SelectPileConstraint("empty", pile => pile.cards.isEmpty)
}

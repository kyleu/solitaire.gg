package models.game.pile.constraints

import models.game.pile.Pile

case class SelectPileConstraint(id: String, f: Pile => Boolean)

object SelectPileConstraints {
  val never = SelectPileConstraint("never", pile => false)

  val empty = SelectPileConstraint("empty", pile => pile.cards.isEmpty)
}

package models.game.pile.constraints

import models.game.GameState
import models.game.pile.{PileOption, Pile}

case class SelectPileConstraint(override val id: String, f: (Pile, GameState) => Boolean) extends PileOption(id)

object SelectPileConstraints {
  val never = SelectPileConstraint("never", (pile, gameState) => false)

  val empty = SelectPileConstraint("empty", (pile, gameState) => pile.cards.isEmpty)
}

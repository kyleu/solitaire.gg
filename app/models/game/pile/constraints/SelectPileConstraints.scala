package models.game.pile.constraints

import models.game.GameState
import models.game.pile.Pile

case class SelectPileConstraint(id: String, f: (Pile, GameState) => Boolean)

object SelectPileConstraints {
  val never = SelectPileConstraint("never", (pile, gameState) => false)

  val empty = SelectPileConstraint("empty", (pile, gameState) => pile.cards.isEmpty)
}

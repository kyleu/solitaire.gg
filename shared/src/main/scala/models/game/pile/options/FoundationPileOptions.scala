package models.game.pile.options

import models.game.pile.constraints.Constraints
import models.game.rules.FoundationRules

object FoundationPileOptions {
  def apply(rules: FoundationRules) = {
    PileOptions(
      cardsShown = Some(1),
      dragFromConstraint = Some(Constraints.topCardOnly),
      dragToConstraint = Some(Constraints.klondikeFoundationDragTo)
    )
  }
}

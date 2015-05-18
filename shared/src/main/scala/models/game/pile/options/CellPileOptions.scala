package models.game.pile.options

import models.game.pile.constraints.Constraint
import models.game.rules.CellRules

object CellPileOptions {
  def apply(rules: CellRules) = {
    if (rules.canMoveFrom.isEmpty) {
      throw new IllegalStateException()
    }
    PileOptions(direction = Some("d"), dragFromConstraint = Some(Constraint.topCardOnly), dragToConstraint = Some(Constraint.empty))
  }
}

package models.game.pile.options

import models.game.pile.constraints.Constraint
import models.game.rules.CellRules

object CellPileOptions {
  def apply(rules: CellRules) = {
    val dragToConstraint = Constraint("cell", (src, tgt, cards, gameState) => {
      src.pileSet.exists(x => rules.mayMoveToFrom.contains(x.behavior))
    })
    PileOptions(direction = Some("d"), dragFromConstraint = Some(Constraint.topCardOnly), dragToConstraint = Some(dragToConstraint))
  }
}

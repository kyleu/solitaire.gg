package models.pile.options

import models.pile.constraints.Constraint
import models.rules.CellRules

object CellPileOptions {
  def apply(rules: CellRules) = {
    val dragToConstraint = Constraint("cell", (src, tgt, cards, gameState) => {
      cards.length == 1 && tgt.cards.isEmpty && src.pileSet.exists(x => rules.mayMoveToFrom.contains(x.behavior))
    })
    PileOptions(direction = Some("d"), dragFromConstraint = Some(Constraint.topCardOnly), dragToConstraint = Some(dragToConstraint))
  }
}

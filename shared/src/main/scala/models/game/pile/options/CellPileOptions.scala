package models.game.pile.options

import models.game.rules.CellRules

object CellPileOptions {
  def apply(rules: CellRules) = {
    if(rules.canMoveFrom == Nil) {
      throw new IllegalStateException()
    }
    PileOptions()
  }
}

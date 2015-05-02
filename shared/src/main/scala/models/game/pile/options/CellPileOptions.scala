package models.game.pile.options

import models.game.rules.CellRules

object CellPileOptions {
  def apply(rules: CellRules) = {
    if (rules.canMoveFrom.isEmpty) {
      throw new IllegalStateException()
    }
    PileOptions()
  }
}

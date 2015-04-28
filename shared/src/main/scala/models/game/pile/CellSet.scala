package models.game.pile

import models.game.pile.options.CellPileOptions
import models.game.rules.CellRules

object CellSet {
  def apply(cellRules: CellRules): CellSet = {
    val prefix = "cell-"
    val options = CellPileOptions(cellRules)
    val piles = (1 to cellRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new CellSet(piles)
  }
}

class CellSet(piles: Seq[Pile]) extends PileSet("cell", piles)

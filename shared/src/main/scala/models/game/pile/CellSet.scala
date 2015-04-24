package models.game.pile

import models.game.rules.CellRules

object CellSet {
  def apply(cellRules: CellRules): CellSet = {
    val prefix = "cell-"
    val options = PileOptions()
    val piles = (1 to cellRules.numPiles).map { i =>
      Pile(prefix + i, "cell", options)
    }
    new CellSet(piles)
  }
}

class CellSet(piles: Seq[Pile]) extends PileSet(piles)

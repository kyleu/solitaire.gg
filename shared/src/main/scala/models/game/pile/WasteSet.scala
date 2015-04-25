package models.game.pile

import models.game.rules.WasteRules

object WasteSet {
  def apply(wasteRules: WasteRules): WasteSet = {
    val prefix = "waste-"
    val options = PileOptionsHelper.waste
    val piles = (1 to wasteRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new WasteSet(piles)
  }
}

class WasteSet(piles: Seq[Pile]) extends PileSet("waste", piles)

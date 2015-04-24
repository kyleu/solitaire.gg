package models.game.pile

import models.game.rules.WasteRules

object WasteSet {
  def apply(wasteRules: WasteRules): WasteSet = {
    val prefix = "waste-"
    val options = PileOptionsHelper.waste
    val piles = (0 until wasteRules.numPiles).map { i =>
      Pile(prefix + i, "waste", options)
    }
    new WasteSet(piles)
  }
}

class WasteSet(piles: Seq[Pile]) extends PileSet(piles)

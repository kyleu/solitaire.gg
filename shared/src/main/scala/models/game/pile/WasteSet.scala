package models.game.pile

import models.game.pile.options.WastePileOptions
import models.game.rules.WasteRules

object WasteSet {
  def apply(rules: WasteRules) = {
    val prefix = "waste-"
    val options = WastePileOptions(rules)
    val piles = (1 to rules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new WasteSet(piles)
  }
}

class WasteSet(piles: Seq[Pile]) extends PileSet("waste", piles)

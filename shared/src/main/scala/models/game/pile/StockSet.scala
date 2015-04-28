package models.game.pile

import models.game.pile.options.StockPileOptions
import models.game.rules.StockRules

object StockSet {
  def apply(rules: StockRules): StockSet = {
    val options = StockPileOptions(rules)
    val piles = Seq(Pile("stock", options))
    new StockSet(piles)
  }
}

class StockSet(piles: Seq[Pile]) extends PileSet("stock", piles)

package models.pile.set

import models.pile.Pile
import models.pile.options.StockPileOptions
import models.rules.StockRules

object StockSet {
  def apply(rules: StockRules, pileIdsByType: Map[String, Seq[String]]): StockSet = {
    val options = StockPileOptions(rules, pileIdsByType)
    val piles = IndexedSeq(Pile("stock", options))
    new StockSet(piles)
  }
}

class StockSet(piles: IndexedSeq[Pile]) extends PileSet(PileSet.Behavior.Stock, piles, visible = true)

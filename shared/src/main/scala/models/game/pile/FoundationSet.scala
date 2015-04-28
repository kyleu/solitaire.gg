package models.game.pile

import models.game.pile.options.FoundationPileOptions
import models.game.rules.FoundationRules

object FoundationSet {
  def apply(foundationRules: FoundationRules): FoundationSet = {
    val prefix = "foundation-"
    val options = FoundationPileOptions(foundationRules)
    val piles = (1 to foundationRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new FoundationSet(piles)
  }
}

class FoundationSet(piles: Seq[Pile]) extends PileSet("foundation", piles)

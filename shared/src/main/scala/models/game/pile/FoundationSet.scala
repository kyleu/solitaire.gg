package models.game.pile

import models.game.rules.FoundationRules

object FoundationSet {
  def apply(FoundationRules: FoundationRules): FoundationSet = {
    val prefix = "foundation-"
    val options = PileOptionsHelper.foundation
    val piles = (1 to FoundationRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new FoundationSet(piles)
  }
}

class FoundationSet(piles: Seq[Pile]) extends PileSet("foundation", piles)

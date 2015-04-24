package models.game.pile

import models.game.rules.FoundationRules

object FoundationSet {
  def apply(FoundationRules: FoundationRules): FoundationSet = {
    val prefix = "foundation-"
    val options = PileOptions()
    val piles = (0 until FoundationRules.numPiles).map { i =>
      Pile(prefix + i, "foundation", options)
    }
    new FoundationSet(piles)
  }
}

class FoundationSet(piles: Seq[Pile]) extends PileSet(piles)

package models.game.pile

import models.game.pile.options.FoundationPileOptions
import models.game.rules.{ DeckOptions, FoundationRules }

object FoundationSet {
  def apply(foundationRules: FoundationRules, deckOptions: DeckOptions): FoundationSet = {
    val prefix = if (foundationRules.setNumber == 0) {
      "foundation-"
    } else {
      "foundation" + (foundationRules.setNumber + 1) + "-"
    }
    val options = FoundationPileOptions(foundationRules, deckOptions)
    val piles = (1 to foundationRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new FoundationSet(piles, foundationRules.visible)
  }
}

class FoundationSet(piles: Seq[Pile], visible: Boolean) extends PileSet("foundation", piles, visible)

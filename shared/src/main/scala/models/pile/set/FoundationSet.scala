package models.pile.set

import models.pile.Pile
import models.pile.options.FoundationPileOptions
import models.rules.{DeckOptions, FoundationRules}

object FoundationSet {
  def apply(foundationRules: FoundationRules, deckOptions: DeckOptions): FoundationSet = {
    val prefix = if (foundationRules.setNumber == 0) {
      "foundation-"
    } else {
      s"foundation${foundationRules.setNumber + 1}-"
    }
    val options = FoundationPileOptions(foundationRules, deckOptions)
    val piles = (1 to foundationRules.numPiles).map { i =>
      Pile(prefix + i, if (options.tail.isEmpty) {
        options.headOption.getOrElse(throw new IllegalStateException())
      } else {
        options(i - 1)
      })
    }
    new FoundationSet(piles, foundationRules.visible)
  }
}

class FoundationSet(piles: IndexedSeq[Pile], visible: Boolean) extends PileSet(PileSet.Behavior.Foundation, piles, visible)

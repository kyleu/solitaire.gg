package models.pile.set

import models.pile.Pile
import models.pile.options.TableauPileOptions
import models.rules.{ CardRemovalMethod, DeckOptions, TableauRules }

object TableauSet {
  def apply(tableauRules: TableauRules, deckOptions: DeckOptions, cardRemovalMethod: CardRemovalMethod): TableauSet = {
    val prefix = if (tableauRules.setNumber == 0) {
      "tableau-"
    } else {
      s"tableau${tableauRules.setNumber + 1}-"
    }

    val options = TableauPileOptions(tableauRules, deckOptions, cardRemovalMethod)
    val piles = (1 to tableauRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new TableauSet(piles)
  }
}

class TableauSet(piles: Seq[Pile]) extends PileSet("tableau", piles)

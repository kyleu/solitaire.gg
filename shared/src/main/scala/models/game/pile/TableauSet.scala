package models.game.pile

import models.game.pile.options.TableauPileOptions
import models.game.rules.TableauRules

object TableauSet {
  def apply(tableauRules: TableauRules): TableauSet = {
    val prefix = if(tableauRules.setNumber == 0) {
      "tableau-"
    } else {
      "tableau" + tableauRules.setNumber + "-"
    }

    val options = TableauPileOptions(tableauRules)
    val piles = (1 to tableauRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new TableauSet(piles)
  }
}

class TableauSet(piles: Seq[Pile]) extends PileSet("tableau", piles)

package models.game.pile

import models.game.rules.TableauRules

object TableauSet {
  def apply(tableauRules: TableauRules): TableauSet = {
    val prefix = "tableau-"
    val options = PileOptions()
    val piles = (0 until tableauRules.numPiles).map { i =>
      Pile(prefix + i, "tableau", options)
    }
    new TableauSet(piles)
  }
}

class TableauSet(piles: Seq[Pile]) extends PileSet(piles)

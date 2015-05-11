package models.game.pile

import models.game.Rank
import models.game.pile.options.PyramidPileOptions
import models.game.rules.{CardRemovalMethod, PyramidRules}

object PyramidSet {
  def apply(pyramidRules: PyramidRules, crm: CardRemovalMethod, lowRank: Rank): PyramidSet = {
    val prefix = if (pyramidRules.setNumber == 0) {
      "pyramid-"
    } else {
      "pyramid" + (pyramidRules.setNumber + 1) + "-"
    }
    val pileOptions = PyramidPileOptions(pyramidRules, crm, lowRank)
    val piles = pileOptions.map {
      case (o, i, j) => Pile(prefix + i + "-" + j, o)
    }
    new PyramidSet(piles)
  }
}

class PyramidSet(piles: Seq[Pile]) extends PileSet("pyramid", piles)

package models.game.pile

import models.game.pile.options.PyramidPileOptions
import models.game.rules.PyramidRules

object PyramidSet {
  def apply(pyramidRules: PyramidRules): PyramidSet = {
    val prefix = "pyramid-"
    val pileOptions = PyramidPileOptions(pyramidRules)
    val piles = pileOptions.map {
      case (o, i, j) => Pile(prefix + i + "-" + j, o)
    }
    new PyramidSet(piles)
  }
}

class PyramidSet(piles: Seq[Pile]) extends PileSet("pyramid", piles)

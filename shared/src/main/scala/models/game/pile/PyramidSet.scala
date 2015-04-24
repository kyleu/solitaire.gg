package models.game.pile

import models.game.rules.PyramidRules

object PyramidSet {
  def apply(pyramidRules: PyramidRules): PyramidSet = {
    val prefix = "pyramid-"
    val options = PileOptions()
    val piles = (1 to pyramidRules.height).flatMap { i =>
      (1 to i).map { j =>
        Pile(prefix + i + "-" + j, "pyramid", options)
      }
    }
    new PyramidSet(piles)
  }
}

class PyramidSet(piles: Seq[Pile]) extends PileSet(piles)

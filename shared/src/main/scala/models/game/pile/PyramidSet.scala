package models.game.pile

import models.game.rules.PyramidRules

object PyramidSet {
  def apply(pyramidRules: PyramidRules): PyramidSet = {
    val prefix = "pyramid-"
    val options = PileOptions()
    val piles = (0 until pyramidRules.height).flatMap { i =>
      (0 until i).map { j =>
        Pile(prefix + i + "-" + j, "pyramid", options)
      }
    }
    new PyramidSet(piles)
  }
}

class PyramidSet(piles: Seq[Pile]) extends PileSet(piles)

package models.game.pile

import models.game.rules.ReserveRules

object ReserveSet {
  def apply(reserveRules: ReserveRules): ReserveSet = {
    val prefix = "reserve-"
    val options = PileOptions()
    val piles = (0 until reserveRules.numPiles).map { i =>
      Pile(prefix + i, "reserve", options)
    }
    new ReserveSet(piles)
  }
}

class ReserveSet(piles: Seq[Pile]) extends PileSet(piles)

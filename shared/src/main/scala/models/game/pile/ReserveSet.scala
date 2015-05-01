package models.game.pile

import models.game.pile.options.ReservePileOptions
import models.game.rules.ReserveRules

object ReserveSet {
  def apply(reserveRules: ReserveRules): ReserveSet = {
    val prefix = "reserve-"
    val options = ReservePileOptions()
    val piles = (1 to reserveRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new ReserveSet(piles)
  }
}

class ReserveSet(piles: Seq[Pile]) extends PileSet("reserve", piles)

package models.pile.set

import models.pile.Pile
import models.pile.options.ReservePileOptions
import models.rules.ReserveRules

object ReserveSet {
  def apply(reserveRules: ReserveRules): ReserveSet = {
    val prefix = "reserve-"
    val options = ReservePileOptions(reserveRules)
    val piles = (1 to reserveRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new ReserveSet(piles)
  }
}

class ReserveSet(piles: Seq[Pile]) extends PileSet("reserve", piles)

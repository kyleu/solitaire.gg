package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.rules.ReserveRules

object ReserveSet {
  def apply(reserveRules: ReserveRules): ReserveSet = {
    val prefix = "reserve-"
    val options = getOptions(reserveRules)
    val piles = (1 to reserveRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new ReserveSet(piles)
  }

  private[this] def getOptions(rules: ReserveRules) = {
    PileOptions(
      cardsShown = Some(1),
      direction = Some("r"),
      selectCardConstraint = Some(Constraints.klondikeSelectCard),
      dragFromConstraint = Some(Constraints.topCardOnly),
      selectCardAction = Some(SelectCardActions.klondike)
    )
  }
}

class ReserveSet(piles: Seq[Pile]) extends PileSet("reserve", piles)

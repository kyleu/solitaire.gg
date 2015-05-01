package models.game.pile.options

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.rules.ReserveRules

object ReservePileOptions {
  def apply() = {
    PileOptions(
      cardsShown = Some(1),
      direction = Some("r"),
      selectCardConstraint = Some(Constraints.klondikeSelectCard),
      dragFromConstraint = Some(Constraints.topCardOnly),
      selectCardAction = Some(SelectCardActions.klondike)
    )
  }
}

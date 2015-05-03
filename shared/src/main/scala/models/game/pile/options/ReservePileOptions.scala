package models.game.pile.options

import models.game.pile.actions.KlondikeActions
import models.game.pile.constraints.Constraints

object ReservePileOptions {
  def apply() = {
    PileOptions(
      cardsShown = Some(1),
      direction = Some("r"),
      selectCardConstraint = Some(Constraints.klondikeSelectCard),
      dragFromConstraint = Some(Constraints.topCardOnly),
      selectCardAction = Some(KlondikeActions.klondike)
    )
  }
}

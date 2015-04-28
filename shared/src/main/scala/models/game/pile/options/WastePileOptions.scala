package models.game.pile.options

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.rules.WasteRules

object WastePileOptions {
  def apply(rules: WasteRules) = {
    PileOptions(
      cardsShown = Some(3),
      direction = Some("r"),
      selectCardConstraint = Some(Constraints.klondikeSelectCard),
      dragFromConstraint = Some(Constraints.topCardOnly),
      selectCardAction = Some(SelectCardActions.klondike)
    )
  }
}

package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints

object Waste {
  val options = PileOptions(
    cardsShown = Some(3),
    direction = Some("r"),
    selectCardConstraint = Constraints.topCardOnly,
    dragFromConstraint = Constraints.topCardOnly,
    selectCardAction = Some(SelectCardActions.klondike)
  )
}

class Waste(id: String, options: PileOptions = Waste.options) extends Pile(id, "waste", options)

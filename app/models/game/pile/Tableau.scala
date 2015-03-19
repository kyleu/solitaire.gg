package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints

object Tableau {
  val options = PileOptions(
    direction = Some("d"),
    selectCardConstraint = Constraints.klondikeSelectCard,
    dragFromConstraint = Constraints.klondikeDragFrom,
    dragToConstraint = Constraints.klondikeTableauDragTo,
    selectCardAction = Some(SelectCardActions.klondike)
  )
}

class Tableau(id: String, options: PileOptions = Tableau.options) extends Pile(id, "tableau", options)

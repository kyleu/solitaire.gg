package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.{DragToConstraints, DragFromConstraints, SelectCardConstraints}

object Tableau {
  private val defaultOptions = PileOptions(
    direction = Some("d"),
    selectCardConstraint = Some(SelectCardConstraints.topCardOnly),
    dragFromConstraint = Some(DragFromConstraints.klondike),
    dragToConstraint = Some(DragToConstraints.klondike),
    selectCardAction = Some(SelectCardActions.klondike)
  )
}

class Tableau(id: String, options: PileOptions = PileOptions.empty) extends Pile(id, "tableau", Tableau.defaultOptions.combine(options))

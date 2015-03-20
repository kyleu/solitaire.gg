package models.game.pile

import models.game.pile.constraints.Constraints

object Foundation {
  val options = PileOptions(
    cardsShown = Some(1),
    dragFromConstraint = Constraints.topCardOnly,
    dragToConstraint = Constraints.klondikeFoundationDragTo
  )
}

class Foundation(id: String, options: PileOptions = Foundation.options) extends Pile(id, "foundation", options)

package models.game.pile

import models.game.pile.constraints.{DragToConstraints, DragFromConstraints}

object Foundation {
  private val defaultOptions = PileOptions(
    cardsShown = Some(1),
    dragFromConstraint = Some(DragFromConstraints.topCardOnly),
    dragToConstraint = Some(DragToConstraints.foundationDefault)
  )
}

class Foundation(id: String, options: PileOptions = PileOptions.empty) extends Pile(id, "foundation", options = Foundation.defaultOptions.combine(options)) {
}

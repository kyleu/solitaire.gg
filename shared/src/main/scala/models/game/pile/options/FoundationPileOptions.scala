package models.game.pile.options

import models.game.pile.constraints.Constraints
import models.game.rules.{ FoundationCanMoveFrom, FoundationRules }

object FoundationPileOptions {
  def apply(rules: FoundationRules) = {
    val dragFromConstraint = rules.canMoveFrom match {
      case FoundationCanMoveFrom.Always => Some(Constraints.always)
      case FoundationCanMoveFrom.EmptyStock => Some(Constraints.pilesEmpty("stock"))
      case FoundationCanMoveFrom.Never => Some(Constraints.never)
    }

    PileOptions(
      cardsShown = Some(rules.cardsShown),
      dragFromConstraint = dragFromConstraint,
      dragToConstraint = Some(Constraints.klondikeFoundationDragTo)
    )
  }
}

package models.game.pile.options

import models.game.pile.constraints.Constraint
import models.game.rules.ReserveRules

object ReservePileOptions {
  def apply(rules: ReserveRules) = {
    val cardsShown = if (rules.initialCards > 0) {
      rules.initialCards
    } else {
      1
    }

    PileOptions(
      cardsShown = Some(cardsShown),
      direction = Some("d"),
      dragFromConstraint = Some(Constraint.topCardOnly)
    )
  }
}

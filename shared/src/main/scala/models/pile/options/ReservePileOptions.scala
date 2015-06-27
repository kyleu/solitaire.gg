package models.pile.options

import models.pile.constraints.Constraint
import models.rules.ReserveRules

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

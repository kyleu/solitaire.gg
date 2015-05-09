package models.game.pile.options

import models.game.pile.constraints.Constraint
import models.game.rules.{ WastePlayableCards, WasteRules }

object WastePileOptions {
  def apply(rules: WasteRules) = {
    val dragFrom = rules.playableCards match {
      case WastePlayableCards.All => Constraint.always
      case WastePlayableCards.TopCardOnly => Constraint.topCardOnly
    }

    PileOptions(
      cardsShown = Some(rules.cardsShown),
      direction = Some("r"),
      dragFromConstraint = Some(dragFrom)
    )
  }
}

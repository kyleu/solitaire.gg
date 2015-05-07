package models.game.pile.options

import models.game.pile.constraints.Constraints
import models.game.rules.{ WastePlayableCards, WasteRules }

object WastePileOptions {
  def apply(rules: WasteRules) = {
    val dragFrom = rules.playableCards match {
      case WastePlayableCards.All => Constraints.always
      case WastePlayableCards.TopCardOnly => Constraints.topCardOnly
    }

    PileOptions(
      cardsShown = Some(rules.cardsShown),
      direction = Some("r"),
      dragFromConstraint = Some(dragFrom)
    )
  }
}

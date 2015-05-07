package models.game.pile.options

import models.game.Rank
import models.game.pile.actions.KlondikeActions
import models.game.pile.constraints.Constraints
import models.game.rules.TableauRules

object TableauPileOptions {
  def apply(rules: TableauRules) = {
    PileOptions(
      direction = Some("d"),
      cardsShown = rules.cardsShown match {
        case 0 => None
        case x => Some(x)
      },
      dragFromConstraint = Some(Constraints.klondikeDragFrom),
      dragToConstraint = Some(Constraints.klondikeTableauDragTo(Some(Rank.King))),
      selectCardConstraint = Some(Constraints.allOf("top-face-down", Constraints.topCardOnly, Constraints.faceDown)),
      selectCardAction = Some(KlondikeActions.flip)
    )
  }
}

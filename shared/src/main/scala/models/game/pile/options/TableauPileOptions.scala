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
      selectCardConstraint = Some(Constraints.klondikeSelectCard),
      dragFromConstraint = Some(Constraints.klondikeDragFrom),
      dragToConstraint = Some(Constraints.klondikeTableauDragTo(Some(Rank.King))),
      selectCardAction = Some(KlondikeActions.klondike)
    )
  }
}

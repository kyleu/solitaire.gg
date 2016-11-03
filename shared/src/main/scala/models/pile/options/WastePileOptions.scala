package models.pile.options

import models.pile.actions.SelectCardActions
import models.pile.constraints.Constraint
import models.rules.{CardRemovalMethod, WastePlayableCards, WasteRules}

object WastePileOptions {
  def apply(rules: WasteRules, crm: CardRemovalMethod) = {
    val dragFrom = rules.playableCards match {
      case WastePlayableCards.All => Constraint.always
      case WastePlayableCards.TopCardOnly => Constraint.topCardOnly
    }

    PileOptions(
      cardsShown = Some(rules.cardsShown),
      direction = Some("r"),
      dragFromConstraint = Some(dragFrom),
      selectCardConstraint = Some(Constraint.allOf("waste-select", Constraint.topCardOnly, Constraint.forCardRemovalMethod(crm))),
      selectCardAction = Some(SelectCardActions.drawToPiles(() => 1, Seq("foundation-1")))
    )
  }
}

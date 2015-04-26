package models.game.pile

import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.rules.WasteRules

object WasteSet {
  def apply(wasteRules: WasteRules): WasteSet = {
    val prefix = "waste-"
    val options = getOptions(wasteRules)
    val piles = (1 to wasteRules.numPiles).map { i =>
      Pile(prefix + i, options)
    }
    new WasteSet(piles)
  }

  private[this] def getOptions(rules: WasteRules) = {
    PileOptions(
      cardsShown = Some(3),
      direction = Some("r"),
      selectCardConstraint = Some(Constraints.klondikeSelectCard),
      dragFromConstraint = Some(Constraints.topCardOnly),
      selectCardAction = Some(SelectCardActions.klondike)
    )
  }
}

class WasteSet(piles: Seq[Pile]) extends PileSet("waste", piles)

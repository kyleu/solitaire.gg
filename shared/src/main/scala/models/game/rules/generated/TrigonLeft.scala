// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TrigonLeft extends GameRules(
  id = "trigonleft",
  title = "Trigon Left",
  description = "A blend of ^trigon^ and ^movingleft^.",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


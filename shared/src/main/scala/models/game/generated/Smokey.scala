// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Smokey extends GameRules(
  id = "smokey",
  title = "Smokey",
  description = "A ^klondike^ variant invented by Ann Edwards where you can build sequences in color, but only move sequences of the same suit. Not" +
  " too hard. Not too easy.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
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
      suitMatchRuleForBuilding = SuitMatchRule.SameColor,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


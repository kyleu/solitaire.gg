// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Spidike extends GameRules(
  id = "spidike",
  title = "Spidike",
  description = "Thomas Warfield's blend of ^spider^ with a ^klondike^ ends up looking a lot like ^spiderette^ except that cards can be moved singl" +
  "y to the foundation.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


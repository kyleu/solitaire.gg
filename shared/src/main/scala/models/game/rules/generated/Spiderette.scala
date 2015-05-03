// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Spiderette extends GameRules(
  id = "spiderette",
  title = "Spiderette",
  description = "A one-deck version of ^spider^, with a ^klondike^-style triangular tableau.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


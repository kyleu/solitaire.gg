// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object QuadrupleTrigon extends GameRules(
  id = "quadrupletrigon",
  title = "Quadruple Trigon",
  description = "A four-deck version of ^trigon^. Needs a large screen.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


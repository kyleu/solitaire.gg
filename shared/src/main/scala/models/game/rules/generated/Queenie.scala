// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Queenie extends GameRules(
  id = "queenie",
  title = "Queenie",
  description = "Build stacks of cards in alternating colors as in ^klondike^, move arbitrary groups of cards as in ^yukon^, and deal waves of card" +
  "s onto to the tableau, as in ^spider^.",
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


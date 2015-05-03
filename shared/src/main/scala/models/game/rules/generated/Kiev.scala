// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Kiev extends GameRules(
  id = "kiev",
  title = "Kiev",
  description = "A version of ^ukrainian^ Solitaire where there is a stock of cards dealt to a rectangular tableau, making the whole thing rather ^" +
  "spider^ish.",
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
      moveCompleteSequencesOnly = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


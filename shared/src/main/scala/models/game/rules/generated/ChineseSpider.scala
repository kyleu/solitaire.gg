// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object ChineseSpider extends GameRules(
  id = "chinesespider",
  title = "Chinese Spider",
  description = "A three-suit version of ^spider^.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(6),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


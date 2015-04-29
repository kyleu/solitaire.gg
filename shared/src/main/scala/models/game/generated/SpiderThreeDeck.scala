// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object SpiderThreeDeck extends GameRules(
  id = "spiderthreedeck",
  title = "Spider Three Deck",
  description = "This three-deck version of ^spider^ is a bit easier than ^bigspider^.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("DDDDU", "DDDDU", "DDDDU", "DDDDU", "DDDDU", "DDDDU", "DDDU", "DDDU", "DDDU", "DDDU", "DDDU", "DDDU"),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


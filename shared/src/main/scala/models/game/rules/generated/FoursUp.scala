// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FoursUp extends GameRules(
  id = "foursup",
  title = "Fours Up",
  description = "Thomas Warfield created this game as a continuation of the series starting with the traditional games ^busyaces^ and ^deuces^. The" +
  " number of tableau piles is again reduced, but now we can build regardless of suit so the game gets a bit easier.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Some(Rank.Four)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


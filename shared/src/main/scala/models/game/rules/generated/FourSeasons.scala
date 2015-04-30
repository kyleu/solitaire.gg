// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FourSeasons extends GameRules(
  id = "fourseasons",
  title = "Four Seasons",
  description = "A simple game of luck and skill where you move cards one at a time, stacking regardless of suit. The five tableau piles are suppos" +
  "ed to be arranged in a cross with the foundation piles in the four corners, but Politaire is still too stupid to do that.",
  deckOptions = DeckOptions(
    lowRank = Some(Rank.Unknown)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


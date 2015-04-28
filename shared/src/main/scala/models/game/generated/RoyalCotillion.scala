// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object RoyalCotillion extends GameRules(
  id = "royalcotillion",
  title = "Royal Cotillion",
  description = "A variation of ^oddandeven^ with some extra tableau and reserve piles, but only one pass allowed through the deck.",
  deckOptions = DeckOptions(
    numDecks = 2
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
      initialCards = InitialCards.PileIndex,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = InitialCards.PileIndex,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = 3,
      cardsFaceDown = 0
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object RoyalRendezvous extends GameRules(
  id = "royalrendezvous",
  title = "Royal Rendezvous",
  description = "An odd Austrian game with four foundation sets, one normal, one for evens, one for odds, and one for kings. No building on the tab" +
  "leau.",
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
      name = "Straight Foundation",
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      name = "Odd Foundation",
      setNumber = 1,
      numPiles = 4,
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      name = "Even Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 3,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 1,
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
  complete = false
)


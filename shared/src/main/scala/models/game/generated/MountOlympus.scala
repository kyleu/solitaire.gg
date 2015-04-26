// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object MountOlympus extends GameRules(
  id = "mountolympus",
  title = "Mount Olympus",
  description = "Build by twos, so odds and evens are in separate sequences on the tableau and separate piles on the foundation.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = InitialCards.PileIndex,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 7,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    ),
    FoundationRules(
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = InitialCards.PileIndex,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.DownBy2,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      rankMatchRuleForMovingStacks = RankMatchRule.DownBy2,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


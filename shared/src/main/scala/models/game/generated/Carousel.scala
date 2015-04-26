// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object Carousel extends GameRules(
  id = "carousel",
  title = "Carousel",
  description = "A two-deck game with separate foundations for aces, evens and odds.",
  deckOptions = DeckOptions(
    numDecks = 2,
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.Ace)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(

    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.AlternatingColors,
      rankMatchRule = RankMatchRule.Equal,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Evens Foundation",
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Odds Foundation",
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 5,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)
// scalastyle:on


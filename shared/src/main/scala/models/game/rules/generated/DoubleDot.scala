// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object DoubleDot extends GameRules(
  id = "doubledot",
  title = "Double Dot",
  description = "An easy game where you build up by twos on the foundation, and down by twos on the tableau.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = 2,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 13,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = 2,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 13,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.DownBy2,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.DownBy2,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


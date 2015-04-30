// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Alternate extends GameRules(
  id = "alternate",
  title = "Alternate",
  description = "A variation of ^sirtommy^ where the foundations are built in alternate color, half upwards, half downwards.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Red Foundation",
      numPiles = 2,
      initialCards = InitialCards.PileIndex,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Black Foundation",
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = InitialCards.PileIndex,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock"),
      mayMoveToEmptyFrom = Seq("Stock")
    )
  ),
  complete = false
)


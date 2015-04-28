// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object StraightFifteens extends GameRules(
  id = "straightfifteens",
  title = "Straight Fifteens",
  description = "An easier varition of ^fifteens^ where tens, jacks, queens and kings are removed in groups containing one of each instead of four " +
  "of a kind.",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsAddingToFifteenOr10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)


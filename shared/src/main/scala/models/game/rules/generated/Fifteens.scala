// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Fifteens extends GameRules(
  id = "fifteens",
  title = "Fifteens",
  description = "A simple game where you remove sets that add to 15 or sets of four tens, four jacks, four queens, or four kings.",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsAddingToFifteenOrFour10JQK,
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


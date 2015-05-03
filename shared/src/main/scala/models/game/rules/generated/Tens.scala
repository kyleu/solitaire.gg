// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Tens extends GameRules(
  id = "tens",
  title = "Tens",
  description = "A set removal game similar to ^simplepairs^ where you can take off pairs that add to 10 or a set four matching cards ten or higher" +
  ". A game of pure luck.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToTenOrFour10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SuitElevens extends GameRules(
  id = "suitelevens",
  title = "Suit Elevens",
  like = Some("elevens"),
  description = "A variation of ^elevens^ where you can only remove sets of cards if they are all of the same suit.",
  cardRemovalMethod = CardRemovalMethod.RemoveSameSuitPairsAddingToElevenOrJQK,
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
      numPiles = 15,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)


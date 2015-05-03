// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Eighteens extends GameRules(
  id = "eighteens",
  title = "Eighteens",
  like = Some("simplepairs"),
  description = "A game where you remove singleton aces, or sets consisting of a face card with three other cards that add to eighteen.",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen,
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
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)


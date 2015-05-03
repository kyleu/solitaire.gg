// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Shuffle extends GameRules(
  id = "shuffle",
  title = "Shuffle",
  like = Some("neptune"),
  description = "A version of ^neptune^ where you can also pair kings with aces.",
  cardRemovalMethod = CardRemovalMethod.RemoveConsecutiveRankPairsOrAK,
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
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)


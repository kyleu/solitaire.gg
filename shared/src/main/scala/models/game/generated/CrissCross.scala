// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object CrissCross extends GameRules(
  id = "crisscross",
  title = "Criss Cross",
  description = "A variation of ^simplepairs^ that requires a very large dose of pure luck to win.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(

    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      offscreen = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)
// scalastyle:on


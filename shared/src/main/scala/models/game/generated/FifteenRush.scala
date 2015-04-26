// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object FifteenRush extends GameRules(
  id = "fifteenrush",
  title = "Fifteen Rush",
  description = "The layout is like ^klondike^, but you remove pairs that add to fifteen or pairs of aces.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFifteenOrAPair,
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(

    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.StockThenWaste
    )
  ),
  complete = false
)
// scalastyle:on


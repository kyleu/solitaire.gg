// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object DoublePyramid extends GameRules(
  id = "doublepyramid",
  title = "Double Pyramid",
  description = "Thomas Warfield's two-deck version of ^pyramid^.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  ),
  complete = false
)


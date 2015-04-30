// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object KingTut extends GameRules(
  id = "kingtut",
  title = "King Tut",
  description = "A pyramid variation where we deal three cards at once and have unlimited redeals.",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
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


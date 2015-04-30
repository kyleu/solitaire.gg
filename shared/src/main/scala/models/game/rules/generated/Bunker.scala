// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Bunker extends GameRules(
  id = "bunker",
  title = "Bunker",
  description = "Build up regardless of suit to try to get all cards onto the tableau.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


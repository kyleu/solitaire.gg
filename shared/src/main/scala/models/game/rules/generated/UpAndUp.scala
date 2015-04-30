// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object UpAndUp extends GameRules(
  id = "upandup",
  title = "Up and Up",
  description = "A variation of ^trustytwelve^ where you can build kings on aces.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


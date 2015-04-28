// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object KnottyNines extends GameRules(
  id = "knottynines",
  title = "Knotty Nines",
  description = "A more difficult variation of ^trustytwelve^.",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


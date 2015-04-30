// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SweetSixteen extends GameRules(
  id = "sweetsixteen",
  title = "Sweet Sixteen",
  description = "A variation of ^trustytwelve^ where you build by alternate color",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


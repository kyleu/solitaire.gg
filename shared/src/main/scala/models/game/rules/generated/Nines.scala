// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Nines extends GameRules(
  id = "nines",
  title = "Nines",
  description = "A variation on ^simplepairs^ pairs that add to 9 or set of ten through king. Much luck required.",
  cardRemovalMethod = CardRemovalMethod.RemoveNinesOrPairsAddingToNineOr10JQK,
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
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  complete = false
)


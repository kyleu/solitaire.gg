// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object AuldLangSyne extends GameRules(
  id = "auldlangsyne",
  title = "Auld Lang Syne",
  description = "An old solitaire game in which no building is allowed on the tableau. The secret to winning is to get extremely lucky before you a" +
  "bandon the game out of shear boredom or to play a more skill-dependent variation like ^sirtommy^ instead.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = InitialCards.PileIndex,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Winery extends GameRules(
  id = "winery",
  title = "Winery",
  description = "A version of ^vineyard^ with cells added.",
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
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 2,
      initialCards = 2
    )
  ),
  complete = false
)


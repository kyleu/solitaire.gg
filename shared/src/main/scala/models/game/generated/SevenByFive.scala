// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object SevenByFive extends GameRules(
  id = "sevenbyfive",
  title = "Seven by Five",
  description = "A ^freecell^ variant with one fewer tableau column and more more cell.",
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
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 5
    )
  ),
  complete = false
)


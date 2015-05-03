// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SevenByFour extends GameRules(
  id = "sevenbyfour",
  title = "Seven by Four",
  like = Some("freecell"),
  description = "A harder ^freecell^ variant with one fewer tableau column.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
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
  cells = Some(CellRules()),
  complete = false
)


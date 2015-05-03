// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TwoCell extends GameRules(
  id = "twocell",
  title = "Two Cell",
  description = "A variation of ^freecell^ with only two cells.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  ),
  complete = false
)


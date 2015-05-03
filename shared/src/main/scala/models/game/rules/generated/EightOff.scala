// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object EightOff extends GameRules(
  id = "eightoff",
  title = "Eight Off",
  related = Seq("eighton"),
  description = "A ^freecell^ variation with more cells, but where you can only build down in the same suit",
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
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 8,
      initialCards = 4
    )
  ),
  complete = false
)


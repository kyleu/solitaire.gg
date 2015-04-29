// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Tuxedo extends GameRules(
  id = "tuxedo",
  title = "Tuxedo",
  description = "An easier variant of ^penguin^ where all cards start on the tableau.",
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
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("UUUUUUUU", "UUUUUUU", "UUUUUUU", "UUUUUUUU", "UUUUUUU", "UUUUUUU", "UUUUUUUU"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 7
    )
  ),
  complete = false
)


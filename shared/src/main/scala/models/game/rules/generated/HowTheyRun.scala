// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object HowTheyRun extends GameRules(
  id = "howtheyrun",
  title = "How They Run",
  description = "A variation of ^threeblindmice^ invented by Erik den Hollander with two cells replacing the two card reserve.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUU",
        "DDDUU",
        "DDDUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
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

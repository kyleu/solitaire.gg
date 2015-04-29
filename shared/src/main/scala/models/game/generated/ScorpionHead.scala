// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object ScorpionHead extends GameRules(
  id = "scorpionhead",
  title = "Scorpion Head",
  description = "A variation of ^scorpion^ with some cells.",
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
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("UUUUUUUU", "UUUUUUUU", "UUUUUUUU", "DDDDUUU", "DDDDUUU", "DDDDUUU", "DDDDUUU"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(CellRules()),
  complete = false
)


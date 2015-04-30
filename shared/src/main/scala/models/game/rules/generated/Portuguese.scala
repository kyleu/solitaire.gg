// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Portuguese extends GameRules(
  id = "portuguese",
  title = "Portuguese",
  description = "A variant of ^bakersdozen^ that allows filling in spaces with kings.",
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
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


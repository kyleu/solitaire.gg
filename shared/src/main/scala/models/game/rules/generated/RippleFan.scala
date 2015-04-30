// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object RippleFan extends GameRules(
  id = "ripplefan",
  title = "Ripple Fan",
  description = "An easier variation of ^cruel^ with one more tableau pile.",
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
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


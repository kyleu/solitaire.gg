// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object PerseveranceB extends GameRules(
  id = "perseveranceb",
  title = "Perseverance B",
  description = "An alternate version of ^perseverancea^ where there are only two redeals, the redeal method is different, and kings are automatica" +
  "lly moved to the bottoms of their stacks.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = InitialCards.PileIndex,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  ),
  complete = false
)


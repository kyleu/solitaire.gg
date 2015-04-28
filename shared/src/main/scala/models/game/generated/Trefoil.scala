// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Trefoil extends GameRules(
  id = "trefoil",
  title = "Trefoil",
  description = "A slightly easier variation of ^labellelucie^ where the aces start on the foundation and there are fewer tableau columns.",
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
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


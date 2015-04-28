// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object ThreeShufflesAndADraw extends GameRules(
  id = "threeshufflesandadraw",
  title = "Three Shuffles and a Draw",
  description = "A variation of ^labellelucie^ that adds a draw.",
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
      numPiles = 18,
      initialCards = InitialCards.Custom,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


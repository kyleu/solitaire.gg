// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object PerseveranceA extends GameRules(
  id = "perseverancea",
  title = "Perseverance A",
  description = "A variation of ^cruel^ where stacks may be moved.",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  ),
  complete = false
)


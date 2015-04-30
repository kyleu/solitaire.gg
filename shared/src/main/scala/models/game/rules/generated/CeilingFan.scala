// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CeilingFan extends GameRules(
  id = "ceilingfan",
  title = "Ceiling Fan",
  description = "An easier variation of ^fan^ where you build in alternate colors.",
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
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


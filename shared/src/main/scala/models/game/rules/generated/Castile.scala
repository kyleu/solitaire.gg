// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Castile extends GameRules(
  id = "castile",
  title = "Castile",
  description = "An open variant of ^bristol^ invented by Thomas Warfield.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = 7,
      cardsFaceDown = 0
    )
  ),
  complete = false
)


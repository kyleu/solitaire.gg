// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Odessa extends GameRules(
  id = "odessa",
  title = "Odessa",
  description = "A variant of ^russian^ with a different starting tableau.",
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
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("DDDUUU", "DDDUUUUU", "DDDUUUUU", "DDDUUUUU", "DDDUUUUU", "DDDUUUUU", "DDDUUU"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


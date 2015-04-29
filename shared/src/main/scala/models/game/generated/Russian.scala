// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Russian extends GameRules(
  id = "russian",
  title = "Russian",
  description = "A harder variation of ^yukon^ where you must build down in the same suit instead of in alternate colors.",
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
      customInitialCards = Seq("U", "DUUUUU", "DDUUUUU", "DDDUUUUU", "DDDDUUUUU", "DDDDDUUUUU", "DDDDDDUUUUU"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


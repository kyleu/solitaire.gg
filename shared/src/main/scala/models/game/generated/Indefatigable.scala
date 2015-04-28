// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Indefatigable extends GameRules(
  id = "indefatigable",
  title = "Indefatigable",
  description = "This variation of ^royalfamily^ is basically the same, but the foundations build up from ace, and it is made even easier by a extr" +
  "a redeal.",
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
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Cruel extends GameRules(
  id = "cruel",
  title = "Cruel",
  related = Seq("indefatigable", "perseverancea", "ripplefan", "unusual"),
  description = "A game where you can redeal the tableau as often as you like, so long as you can take off at least one card between deals.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


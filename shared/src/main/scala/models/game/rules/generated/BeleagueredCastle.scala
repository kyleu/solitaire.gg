// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BeleagueredCastle extends GameRules(
  id = "beleagueredcastle",
  title = "Beleaguered Castle",
  description = "A challenging game with simple rules. All cards start dealt face up and you build down regardless of suit, moving only single card" +
  "s. Somewhat similar to ^bakersdozen^.",
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
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Stewart extends GameRules(
  id = "stewart",
  title = "Stewart",
  like = Some("martha"),
  description = "A more difficult variation of ^martha^ in which only single cards may be moved. It is similar to ^bakersdozen^.",
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
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


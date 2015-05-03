// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Vineyard extends GameRules(
  id = "vineyard",
  title = "Vineyard",
  description = "A difficult variation of ^bakersdozen^ invented by Peter Voke.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


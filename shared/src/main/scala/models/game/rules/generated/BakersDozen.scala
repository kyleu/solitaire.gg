// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BakersDozen extends GameRules(
  id = "bakersdozen",
  title = "Baker's Dozen",
  description = "Rearrange the thirteen tableau piles to free up cards for the foundation by moving one card at a time. Often winnable, but takes s" +
  "ome planning.",
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
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object EightOn extends GameRules(
  id = "eighton",
  title = "Eight On",
  like = Some("eightoff"),
  description = "A harder variation of ^eightoff^ where the aces start on the bottoms of the piles. Invented by Thomas Warfield.",
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
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      pilesWithLowCardsAtBottom = 4
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 8,
      initialCards = 8
    )
  ),
  complete = false
)


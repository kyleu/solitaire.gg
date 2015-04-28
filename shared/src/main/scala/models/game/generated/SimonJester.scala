// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object SimonJester extends GameRules(
  id = "simonjester",
  title = "Simon Jester",
  description = "A two-deck variant of ^simplesimon^ invented by Adam Selene. It is like ^spider^ except that all cards start face up in a triangul" +
  "ar tableau and there are no further cards be dealt.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      offscreen = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.Custom,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


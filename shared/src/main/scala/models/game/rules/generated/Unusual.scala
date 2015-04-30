// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Unusual extends GameRules(
  id = "unusual",
  title = "Unusual",
  description = "A two-deck version of ^cruel^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = InitialCards.PileIndex,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 24,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FreeCellFourDeck extends GameRules(
  id = "freecellfourdeck",
  title = "FreeCell Four Deck",
  like = Some("freecellthreedeck"),
  description = "A four-deck version of ^freecell^ for those who like spending a long time solving a single deal.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 14
    )
  ),
  complete = false
)


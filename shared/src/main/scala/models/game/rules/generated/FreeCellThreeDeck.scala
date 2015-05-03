// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FreeCellThreeDeck extends GameRules(
  id = "freecellthreedeck",
  title = "FreeCell Three Deck",
  like = Some("triplefreecell"),
  related = Seq("freecellfourdeck"),
  description = "Yet another three-deck version of ^freecell^.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 12
    )
  ),
  complete = false
)


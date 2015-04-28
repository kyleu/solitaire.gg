// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object MissMilligan extends GameRules(
  id = "missmilligan",
  title = "Miss Milligan",
  description = "Starting with one card in each column, build sequences down by alternate color. Deal new cards from the deck into all columns. Whe" +
  "n the deck is empty, gain a reserve area you can waive a stack of cards into.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1),
      createPocketWhenEmpty = true
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


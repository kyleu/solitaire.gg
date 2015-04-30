// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object QuadrupleKlondike extends GameRules(
  id = "quadrupleklondike",
  title = "Quadruple Klondike",
  description = "A four-deck variation of ^klondike^, invented by Thomas Warfield. You will need a large screen for this. Try using the F11 key to " +
  "put your browser into full-screen mode.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object NumberTen extends GameRules(
  id = "numberten",
  title = "Number Ten",
  description = "Like ^fortythieves^, but two cards in each tableau stack are dealt face down, we build in alternating colors, and can move stacks " +
  "as a whole.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(2),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


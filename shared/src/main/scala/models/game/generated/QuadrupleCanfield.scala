// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object QuadrupleCanfield extends GameRules(
  id = "quadruplecanfield",
  title = "Quadruple Canfield",
  description = "An easy four-deck version of ^canfield^ invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 4,
    lowRank = Some(Rank.Unknown)
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(

    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 39,
      cardsFaceDown = 100
    )
  ),
  complete = false
)
// scalastyle:on


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TripleCanfield extends GameRules(
  id = "triplecanfield",
  title = "Triple Canfield",
  description = "An easy three-deck version of ^canfield^ invented by Thomas Warfield that has fewer tableau piles and a smaller reserve than ^thre" +
  "edemons^.",
  deckOptions = DeckOptions(
    numDecks = 3,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      initialCards = 1,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
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
      initialCards = 26,
      cardsFaceDown = 100
    )
  ),
  complete = false
)


// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object ThreeDemons extends GameRules(
  id = "threedemons",
  title = "Three Demons",
  description = "This three-deck version of ^canfield^ invented by Thomas Warfield starts with more cards in the reserve and more tableau piles tha" +
  "n ^triplecanfield^.",
  deckOptions = DeckOptions(
    numDecks = 3,
    lowRank = Some(Rank.Unknown)
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
      initialCards = InitialCards.Count(1),
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 48,
      cardsFaceDown = 100
    )
  ),
  complete = false
)


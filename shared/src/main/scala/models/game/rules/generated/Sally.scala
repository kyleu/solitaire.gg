// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Sally extends GameRules(
  id = "sally",
  title = "Sally",
  like = Some("doubleklondike"),
  description = "A version of ^doubleklondike^ where the base card of the foundation depends on a card dealt in.",
  deckOptions = DeckOptions(
    numDecks = 2,
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
      numPiles = 8,
      initialCards = 1,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


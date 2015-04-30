// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SuperiorCanfield extends GameRules(
  id = "superiorcanfield",
  title = "Superior Canfield",
  description = "Canfield made a bit easier and a bit more strategic by dealing the reserve cards face up and not automatically filling spaces from" +
  " the reserve.",
  deckOptions = DeckOptions(
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
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 0
    )
  ),
  complete = false
)


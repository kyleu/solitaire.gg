// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CanfieldGalleryMode extends GameRules(
  id = "canfieldgallery",
  title = "Canfield (Gallery Mode)",
  description = "This is just ^canfield^ with a different user \"interface\": all the cards that would normally start in the stock are fanned out f" +
  "ace up, with the ones that would normally be playable if you were going through the stock three at a time automatically raised up " +
  "to indicate that they are playable.",
  deckOptions = DeckOptions(
    lowRank = Some(Rank.Unknown)
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(
      name = "Gallery"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
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
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 100
    )
  ),
  complete = false
)


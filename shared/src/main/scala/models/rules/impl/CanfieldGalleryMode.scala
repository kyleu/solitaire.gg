package models.rules.impl

import models.card.Rank
import models.rules._

object CanfieldGalleryMode extends GameRules(
  id = "canfieldgallery",
  completed = false,
  title = "Canfield (Gallery Mode)",
  like = Some("canfield"),
  layout = "swf|r|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules(name = "Gallery")),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)

package models.rules.impl

import models.rules._

object SextupleKlondike extends GameRules(
  id = "sextupleklondike",
  completed = true,
  title = "Sextuple Klondike",
  layout = "swf|::t",
  deckOptions = DeckOptions(numDecks = 6),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 24,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 23,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

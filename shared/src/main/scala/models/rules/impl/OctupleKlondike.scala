package models.rules.impl

import models.rules._

object OctupleKlondike extends GameRules(
  id = "octupleklondike",
  completed = true,
  title = "Octuple Klondike",
  layout = "swf|::::.t",
  deckOptions = DeckOptions(numDecks = 8),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 32,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 26,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

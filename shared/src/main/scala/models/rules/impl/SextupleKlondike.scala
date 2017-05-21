package models.rules.impl

import models.rules._

object SextupleKlondike extends GameRules(
  id = "sextupleklondike",
  completed = true,
  title = "Sextuple Klondike",
  layout = "swf|::.t",
  deckOptions = DeckOptions(numDecks = 6),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 24,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 22,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

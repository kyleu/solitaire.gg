package models.rules.impl

import models.rules._

object InsaneKlondike extends GameRules(
  id = "insaneklondike",
  completed = true,
  title = "Insane Klondike",
  layout = "sw|2f|::::::t",
  deckOptions = DeckOptions(numDecks = 32),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 32 * 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 52,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

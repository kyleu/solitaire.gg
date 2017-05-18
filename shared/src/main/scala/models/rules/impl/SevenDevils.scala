package models.rules.impl

import models.rules._

object SevenDevils extends GameRules(
  id = "sevendevils",
  completed = true,
  title = "Seven Devils",
  layout = "swf|r:t",
  like = Some("klondike"),
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  reserves = Some(ReserveRules(initialCards = 13)),
  foundations = Seq(FoundationRules(numPiles = 8)),
  tableaus = Seq(TableauRules(emptyFilledWith = FillEmptyWith.HighRank))
)

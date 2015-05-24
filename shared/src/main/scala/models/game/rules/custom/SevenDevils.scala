package models.game.rules.custom

import models.game.rules._

object SevenDevils extends GameRules(
  id = "sevendevils",
  title = "Seven Devils",
  description = "",
  like = Some("klondike"),
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  reserves = Some(ReserveRules(initialCards = 13)),
  foundations = Seq(FoundationRules(numPiles = 8)),
  tableaus = Seq(TableauRules(emptyFilledWith = FillEmptyWith.HighRank))
)


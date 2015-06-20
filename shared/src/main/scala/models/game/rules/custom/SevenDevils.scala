package models.game.rules.custom

import models.game.rules._

object SevenDevils extends GameRules(
  id = "sevendevils",
  title = "Seven Devils",
  description = "Seven Devils is arguably the most difficult of all solitaire games. Good luck!",
  like = Some("klondike"),
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  reserves = Some(ReserveRules(initialCards = 13)),
  foundations = Seq(FoundationRules(numPiles = 8)),
  tableaus = Seq(TableauRules(emptyFilledWith = FillEmptyWith.HighRank))
)


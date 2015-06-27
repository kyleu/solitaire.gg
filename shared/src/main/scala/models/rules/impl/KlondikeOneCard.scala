package models.rules.impl

import models.rules._

object KlondikeOneCard extends GameRules(
  id = "klondike1card",
  completed = true,
  custom = true,
  title = "Klondike (One Card)",
  description = "The world's most famous solitaire game features a triangular tableau where you build down in alternating colors. " +
  "In this version, you draw one card from the stock at a time.",
  like = Some("klondike"),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4)),
  tableaus = Seq(TableauRules(emptyFilledWith = FillEmptyWith.HighRank))
)


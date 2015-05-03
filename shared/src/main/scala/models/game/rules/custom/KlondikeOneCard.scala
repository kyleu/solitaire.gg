package models.game.rules.custom

import models.game.rules._

object KlondikeOneCard extends GameRules(
  id = "klondike1card",
  title = "Klondike (One Card)",
  description = "The world's most famous solitaire game features a triangular tableau where you build down in alternating colors. " +
    "In this version, you draw one card from the stock at a time.",
  like = Some("klondike"),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Always,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


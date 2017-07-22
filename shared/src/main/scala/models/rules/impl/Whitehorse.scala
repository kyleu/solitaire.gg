package models.rules.impl

import models.rules._

object Whitehorse extends GameRules(
  id = "whitehorse",
  completed = true,
  title = "Whitehorse",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/whitehorse.htm")),
  layout = "swf|t",
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    initialCards = InitialCards.Count(1),
    autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)

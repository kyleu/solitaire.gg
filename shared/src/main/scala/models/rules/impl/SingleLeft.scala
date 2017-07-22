package models.rules.impl

import models.rules._

object SingleLeft extends GameRules(
  id = "singleleft",
  completed = false,
  title = "Single Left",
  like = Some("movingleft"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/single_left.htm")),
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

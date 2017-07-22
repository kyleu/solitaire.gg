package models.rules.impl

import models.rules._

object Spike extends GameRules(
  id = "spike",
  completed = false,
  title = "Spike",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spike.htm")),
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

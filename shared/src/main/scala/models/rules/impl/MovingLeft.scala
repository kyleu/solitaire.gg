package models.rules.impl

import models.rules._

object MovingLeft extends GameRules(
  id = "movingleft",
  completed = true,
  title = "Moving Left",
  related = Seq("tripleleft", "singleleft"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/moving_left.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

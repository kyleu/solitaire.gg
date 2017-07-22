package models.rules.impl

import models.rules._

object GoldRush extends GameRules(
  id = "goldrush",
  completed = true,
  title = "Gold Rush",
  related = Seq("doublegoldrush"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gold_rush.htm")),
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.FewerEachTime
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
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

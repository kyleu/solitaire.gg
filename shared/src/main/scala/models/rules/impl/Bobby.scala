package models.rules.impl

import models.rules._

object Bobby extends GameRules(
  id = "bobby",
  completed = false,
  title = "Bobby",
  like = Some("robert"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bobby.htm")),
  layout = "swf",
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  )
)

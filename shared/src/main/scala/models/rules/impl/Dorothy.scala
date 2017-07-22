package models.rules.impl

import models.card.Rank
import models.rules._

object Dorothy extends GameRules(
  id = "dorothy",
  completed = false,
  title = "Dorothy",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dorothy.htm")),
  layout = "swfff",
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Nine),
      rankMatchRule = RankMatchRule.DownBy2,
      maxCards = 5
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Ten),
      rankMatchRule = RankMatchRule.DownBy2,
      maxCards = 5
    ),
    FoundationRules(
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Jack),
      maxCards = 3
    )
  )
)

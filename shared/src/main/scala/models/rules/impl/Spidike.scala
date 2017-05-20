package models.rules.impl

import models.rules._

object Spidike extends GameRules(
  id = "spidike",
  completed = false,
  title = "Spidike",
  like = Some("spiderette"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spidike.htm")),
  layout = "sf|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

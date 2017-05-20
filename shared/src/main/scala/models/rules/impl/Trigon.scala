package models.rules.impl

import models.rules._

object Trigon extends GameRules(
  id = "trigon",
  completed = true,
  title = "Trigon",
  like = Some("klondike"),
  related = Seq("doubletrigon", "quadrupletrigon", "trigonleft"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trigon.htm"),
    Link("Jan Wolter's Experiments", "/article/trigon.html")
  ),
  layout = "swf|t",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

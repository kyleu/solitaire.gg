package models.rules.impl

import models.rules._

object Chinaman extends GameRules(
  id = "chinaman",
  completed = false,
  title = "Chinaman",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chinaman.htm")),
  layout = "swf|t",
  stock = Some(StockRules(maximumDeals = Some(2), cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.DifferentSuits,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

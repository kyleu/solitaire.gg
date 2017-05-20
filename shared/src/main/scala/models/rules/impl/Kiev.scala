package models.rules.impl

import models.rules._

object Kiev extends GameRules(
  id = "kiev",
  completed = false,
  title = "Kiev",
  related = Seq("dnieper", "sevastopol"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kiev.htm")),
  layout = "s::f|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any
    )
  )
)

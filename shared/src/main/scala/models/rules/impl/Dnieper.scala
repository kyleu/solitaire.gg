package models.rules.impl

import models.rules._

object Dnieper extends GameRules(
  id = "dnieper",
  completed = false,
  title = "Dnieper",
  like = Some("kiev"),
  layout = "sf|t",
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 4, moveCompleteSequencesOnly = true, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    initialCards = InitialCards.Count(4),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    wrap = true,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any
  ))
)

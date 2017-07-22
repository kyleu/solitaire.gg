package models.rules.impl

import models.rules._

object Sevastopol extends GameRules(
  id = "sevastopol",
  completed = true,
  title = "Sevastopol",
  like = Some("kiev"),
  layout = "s::f|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDU",
        "DDDU",
        "DDU",
        "DDDU",
        "DDU",
        "DDDU",
        "DDU"
      ),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any
    )
  )
)

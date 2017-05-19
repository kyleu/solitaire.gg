package models.rules.impl

import models.rules._

object Ants extends GameRules(
  id = "ants",
  completed = true,
  title = "Ants",
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/ants.htm")),
  layout = "sf|.t",
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 4,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)

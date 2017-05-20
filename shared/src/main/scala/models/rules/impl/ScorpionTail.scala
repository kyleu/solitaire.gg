package models.rules.impl

import models.rules._

object ScorpionTail extends GameRules(
  id = "scorpiontail",
  completed = false,
  title = "Scorpion Tail",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/scorpion_tail.htm")),
  layout = "sf|t",
  stock = Some(
    StockRules(
      name = "Reserve",
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

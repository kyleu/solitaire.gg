package models.rules.impl

import models.rules._

object Chinese extends GameRules(
  id = "chinese",
  completed = true,
  title = "Chinese",
  like = Some("scorpion"),
  related = Seq("dragon"),
  links = Seq(Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/chinese_solitaire.html")),
  layout = "s.f|t",
  stock = Some(StockRules(name = "Reserve", dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "DDDUUUU",
        "DDDDUUU",
        "DDDDDUU",
        "DDDDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

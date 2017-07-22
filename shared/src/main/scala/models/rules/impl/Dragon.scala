package models.rules.impl

import models.rules._

object Dragon extends GameRules(
  id = "dragon",
  completed = true,
  title = "Dragon",
  like = Some("chinese"),
  links = Seq(Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/dragon.html")),
  layout = "s.f|t",
  stock = Some(StockRules(name = "Reserve", dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "UUUUUUUU",
      "DUUUUUUU",
      "DDUUUUUU",
      "DDDUUUU",
      "DDDDUUU",
      "DDDDDUU",
      "DDDDDDU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any
  ))
)

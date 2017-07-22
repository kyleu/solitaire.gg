package models.rules.impl

import models.rules._

object Bunker extends GameRules(
  id = "bunker",
  completed = true,
  title = "Bunker",
  like = Some("trustytwelve"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bunker.htm")),
  layout = "s:::w|t",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(StockRules(cardsShown = 19, maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)

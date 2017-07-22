package models.rules.impl

import models.rules._

object UpAndUp extends GameRules(
  id = "upandup",
  completed = true,
  title = "Up and Up",
  like = Some("trustytwelve"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/up_and_up.htm")),
  layout = ":::s|t",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)

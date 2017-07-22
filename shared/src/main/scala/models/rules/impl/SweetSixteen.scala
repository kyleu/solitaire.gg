package models.rules.impl

import models.rules._

object SweetSixteen extends GameRules(
  id = "sweetsixteen",
  completed = false,
  title = "Sweet Sixteen",
  like = Some("trustytwelve"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sweet_sixteen.htm")),
  layout = "s|t",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 16,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)

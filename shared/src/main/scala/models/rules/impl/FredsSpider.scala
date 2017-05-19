package models.rules.impl

import models.rules._

object FredsSpider extends GameRules(
  id = "fredsspider",
  completed = true,
  title = "Fred's Spider",
  like = Some("spider"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/freds_spider.htm")),
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)

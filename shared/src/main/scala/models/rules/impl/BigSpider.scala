package models.rules.impl

import models.rules._

object BigSpider extends GameRules(
  id = "bigspider",
  completed = true,
  title = "Big Spider",
  like = Some("spider"),
  related = Seq("spiderthreedeck"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_spider.htm")),
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(StockRules(dealTo = StockDealTo.TableauIfNoneEmpty, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 12, moveCompleteSequencesOnly = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(6),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)

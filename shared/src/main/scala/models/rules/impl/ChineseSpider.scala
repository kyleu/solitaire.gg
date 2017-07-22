package models.rules.impl

import models.card.Suit
import models.rules._

object ChineseSpider extends GameRules(
  id = "chinesespider",
  completed = true,
  title = "Chinese Spider",
  like = Some("spider"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chinese_spider.htm")),
  layout = "s:f|:::t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 4, suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds)),
  stock = Some(StockRules(dealTo = StockDealTo.TableauIfNoneEmpty, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 16, moveCompleteSequencesOnly = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(6),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)

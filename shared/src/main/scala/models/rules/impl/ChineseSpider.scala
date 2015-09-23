package models.rules.impl

import models.card.Suit
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau cards face down (T0df): 100
 *   Custom initial cards (T0ds): DDDDDU DDDDDU DDDDDU DDDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): spider
 *   Number of decks (ndecks): 4 (4 decks)
 *   Right mouse interface function (rightfunc): 0
 *   Custom suits (suits): 35
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object ChineseSpider extends GameRules(
  id = "chinesespider",
  completed = false,
  title = "Chinese Spider",
  like = Some("spider"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chinese_spider.htm")),
  description = "A three-suit version of ^spider^.",
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 4,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds)
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(6),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)

package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation add complete sequences only (F0cs): false
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Custom initial cards (T0ds): DDDDDU DDDDDU DDDDDU DDDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): spider
 *   Number of decks (ndecks): 2 (2 decks)
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object FredsSpider extends GameRules(
  id = "fredsspider",
  completed = true,
  title = "Fred's Spider",
  like = Some("spider"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/freds_spider.htm")),
  description = "In this easy variation on ^spider^, designed by Fred Lunde of Livonia, Michigan, cards are dealt face up and can be moved to the f" +
    "oundation singly.",
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
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

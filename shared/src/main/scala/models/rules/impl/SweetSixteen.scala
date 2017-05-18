package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   *S0cardsShown (S0cardsShown): 19
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Cards shown (T0cardsShown): 2
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Similar to (like): trustytwelve
 *   Number of decks (ndecks): 1 (1 deck)
 *   Victory condition (victory): 2 (No cards left in stock)
 */
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
  tableaus = Seq(
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

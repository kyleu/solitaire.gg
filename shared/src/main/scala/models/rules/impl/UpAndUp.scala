package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   *S0cardsShown (S0cardsShown): 19
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Cards shown (T0cardsShown): 2
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 10
 *   Tableau rank match rule for building (T0r): 128 (Build up)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Similar to (like): trustytwelve
 *   Number of decks (ndecks): 1 (1 deck)
 *   Victory condition (victory): 2 (No cards left in stock)
 */
object UpAndUp extends GameRules(
  id = "upandup",
  completed = true,
  title = "Up and Up",
  like = Some("trustytwelve"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/up_and_up.htm")),
  description = "A variation of ^trustytwelve^ where you can build kings on aces.",
  layout = ":::s|t",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
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

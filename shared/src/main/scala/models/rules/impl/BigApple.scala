package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 3
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled from (T0fo): 17
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Similar to (like): gotham
 *   Low card (lowpip): -2 (?)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object BigApple extends GameRules(
  id = "bigapple",
  completed = false,
  title = "Big Apple",
  like = Some("gotham"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_apple.htm")),
  description = "A difficult variation of ^newyork^ with three cells instead of three waste piles, but where stacks can be moved.",
  layout = "sf|c|t",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
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
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      mayMoveToEmptyFrom = Seq("stock", "cell")
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 3
    )
  )
)

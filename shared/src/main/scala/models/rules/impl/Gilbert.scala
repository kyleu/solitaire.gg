package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Foundation Sets (Fn): 2
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 8 (Sevens only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 */
object Gilbert extends GameRules(
  id = "gilbert",
  completed = false,
  title = "Gilbert",
  like = Some("klondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gilbert.htm")),
  description = "An odd ^klondike^ variation with one set of foundations building up and one set building down. The fact that only sevens can fill " +
    "gaps in the tableau makes it nearly unplayable.",
  layout = "swff|t",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.Sevens
    )
  )
)

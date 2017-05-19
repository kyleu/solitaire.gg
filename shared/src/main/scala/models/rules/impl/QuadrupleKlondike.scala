package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 4 (4 decks)
 */
object QuadrupleKlondike extends GameRules(
  id = "quadrupleklondike",
  completed = true,
  title = "Quadruple Klondike",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_klondike.htm")),
  layout = "swf|:.t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

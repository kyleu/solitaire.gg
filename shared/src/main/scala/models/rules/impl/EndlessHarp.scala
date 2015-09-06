// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): bigharp
 */
object EndlessHarp extends GameRules(
  id = "endlessharp",
  completed = true,
  title = "Endless Harp",
  like = Some("klondike"),
  related = Seq("bigharp"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/endless_harp.htm")),
  description = "A variation of Big Harp which allows unlimited redeals.",
  layout = "swf|.t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

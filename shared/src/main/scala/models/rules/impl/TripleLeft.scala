package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): -1 (Next tableau column)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Similar to (like): movingleft
 *   Number of decks (ndecks): 3 (3 decks)
 */
object TripleLeft extends GameRules(
  id = "tripleleft",
  completed = true,
  title = "Triple Left",
  like = Some("movingleft"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_left.htm")),
  layout = "swf|.:t",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

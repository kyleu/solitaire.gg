// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-fill an empty tableau from (T0af): -1 (Next tableau column)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Similar to (like): movingleft
 *   Number of decks (ndecks): 1 (1 deck)
 */
object SingleLeft extends GameRules(
  id = "singleleft",
  title = "Single Left",
  like = Some("movingleft"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/single_left.htm")),
  description = "Thomas Warfield's one-deck version of ^movingleft^.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

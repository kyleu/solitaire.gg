// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation add complete sequences only (F0cs): false
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Custom initial cards (T0ds): DDDDDU DDDDDU DDDDDU DDDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): spiderette
 *   Number of decks (ndecks): 1 (1 deck)
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 0 (All cards on foundation)
 */
object Spidike extends GameRules(
  id = "spidike",
  title = "Spidike",
  like = Some("spiderette"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spidike.htm")),
  description = "Thomas Warfield's blend of ^spider^ with a ^klondike^ ends up looking a lot like ^spiderette^ except that cards can be moved singl" +
    "y to the foundation.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

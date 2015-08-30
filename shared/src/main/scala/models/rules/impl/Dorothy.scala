// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation low rank (F0b): 9 (9)
 *   Maximum cards for foundation (F0m): 5
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation rank match rule (F0r): 16 (Build down by 2)
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 10 (X)
 *   Maximum cards for foundation (F1m): 5
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 16 (Build down by 2)
 *   Auto-move cards to foundation (F2a): 0 (Never)
 *   Foundation low rank (F2b): 11 (J)
 *   Maximum cards for foundation (F2m): 3
 *   Number of foundation piles (F2n): 4 (4 stacks)
 *   Foundation rank match rule (F2r): 128 (Build up)
 *   Foundation Sets (Fn): 3
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Maximum deals from stock (maxdeals): 2 (2)
 */
object Dorothy extends GameRules(
  id = "dorothy",
  completed = false,
  title = "Dorothy",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dorothy.htm")),
  description = "Another brainless variation of ^captivequeens^ and ^sixesandsevens^ with separate foundations for odds, evens and face cards.",
  layout = "swfff",
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Nine),
      rankMatchRule = RankMatchRule.DownBy2,
      maxCards = 5
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Ten),
      rankMatchRule = RankMatchRule.DownBy2,
      maxCards = 5
    ),
    FoundationRules(
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Jack),
      maxCards = 3
    )
  )
)
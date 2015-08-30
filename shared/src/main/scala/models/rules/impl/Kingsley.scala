// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 22 (Deck's high card)
 *   Foundation rank match rule (F0r): 32 (Build down)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 7 (Aces only)
 *   Tableau piles (T0n): 7
 *   Tableau rank match rule for building (T0r): 128 (Build up)
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau rank match rule for moving stacks (T0tr): 128 (Build up)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): klondike
 *   Maximum deals from stock (maxdeals): 1 (1)
 */
object Kingsley extends GameRules(
  id = "kingsley",
  completed = true,
  title = "Kingsley",
  like = Some("klondike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingsley.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/kingsley.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Kingsley.htm")
  ),
  description = "Reverse ^klondike^ which in theory is no more difficult, but which proves hard to wrap your head around if you are used to playing" +
    " it the other way round.",
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      rankMatchRuleForBuilding = RankMatchRule.Up,
      rankMatchRuleForMovingStacks = RankMatchRule.Up,
      emptyFilledWith = FillEmptyWith.LowRank
    )
  )
)
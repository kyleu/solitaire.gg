// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Maximum cards for foundation (F0m): 0
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   Foundation rank match rule (F0r): 160 (Build up or down)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Similar to (like): robert
 *   Maximum deals from stock (maxdeals): 3 (3)
 */
object Bobby extends GameRules(
  id = "bobby",
  title = "Bobby",
  like = Some("robert"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bobby.htm")),
  description = "A variation of ^robert^ with a second foundation pile to make it easier, but not much easier.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  )
)

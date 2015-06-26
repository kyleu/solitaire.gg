// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Maximum cards for foundation (F0m): 0
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation rank match rule (F0r): 160 (Build up or down)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Related games (related): bobby
 */
object Robert extends GameRules(
  id = "robert",
  title = "Robert",
  related = Seq("bobby"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/robert.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/robert.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/robert.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/robert.htm")
  ),
  description = "An nearly unwinnable game with no tableau.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  )
)

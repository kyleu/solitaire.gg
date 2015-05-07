// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Deal cards from stock (dealto): 9
 *   Left mouse interface function (leftfunc): 0x1
 *   Similar to (like): pyramid
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Card removal method (pairs): 17 (Remove pairs with consecutive or equal ranks)
 */
object Cheops extends GameRules(
  id = "cheops",
  title = "Cheops",
  like = Some("pyramid"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cheops.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/cheops.htm")
  ),
  description = "A variation of ^pyramid^ where you remove pairs of cards with equal or consecutive ranks",
  cardRemovalMethod = CardRemovalMethod.RemoveConsecutiveOrEqualRankPairs,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  ),
  complete = false
)


// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   *P0ds (P0ds): ------
 *   *P0n (P0n): 7
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Number of waste piles (W0n): 3
 *   Deal cards from stock (dealto): 9
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): triangle
 *   Card removal method (pairs): 7 (Remove pairs adding to 11, J-J, Q-Q, or K-K)
 */
object ElevenTriangle extends GameRules(
  id = "eleventriangle",
  title = "Eleven Triangle",
  like = Some("triangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eleven_triangle.htm")),
  description = "A somewhat easier version of ^triangle^ where we remove pairs that add to eleven.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQPairOrKPair,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
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
  )
)

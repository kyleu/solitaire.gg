// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   *P0ds (P0ds): ------
 *   *P0n (P0n): 7
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Number of waste piles (W0n): 2
 *   Deal cards from stock (dealto): 9
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 *   Related games (related): eleventriangle
 */
object Triangle extends GameRules(
  id = "triangle",
  title = "Triangle",
  related = Seq("eleventriangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triangle.htm")),
  description = "A very hard inverted version of ^pyramid^.",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.WasteOrPairManually,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 2
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

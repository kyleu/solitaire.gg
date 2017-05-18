package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Number of waste piles (W0n): 3
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): pyramid
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 */
object Apophis extends GameRules(
  id = "apophis",
  completed = false,
  title = "Apophis",
  like = Some("pyramid"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/apophis.htm")),
  layout = "swf|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
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

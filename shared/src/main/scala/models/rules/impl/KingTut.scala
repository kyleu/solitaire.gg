// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): pyramid
 *   Maximum deals from stock (maxdeals): 0
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 *   Victory condition (victory): 4
 */
object KingTut extends GameRules(
  id = "kingtut",
  title = "King Tut",
  like = Some("pyramid"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/king_tut.htm"),
    Link("Michael Keller's Discussion", "www.solitairelaboratory.com/pyramid.html")
  ),
  description = "A pyramid variation where we deal three cards at once and have unlimited redeals.",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
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

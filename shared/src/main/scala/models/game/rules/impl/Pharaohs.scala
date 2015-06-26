// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   *P1ds (P1ds): +++++
 *   *P2ds (P2ds): +
 *   Number of pyramids (Pn): 3 (3 pyramids)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 1
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 */
object Pharaohs extends GameRules(
  id = "pharaohs",
  title = "Pharaohs",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pharaohs.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Pharaohs.htm")
  ),
  description = "A variation of ^pyramid^ with three pyramids.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
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
    ),
    PyramidRules(
      setNumber = 1,
      height = 6,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    ),
    PyramidRules(
      setNumber = 2,
      height = 2,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  )
)

package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Cheops extends GameRules(
  id = "cheops",
  completed = false,
  title = "Cheops",
  like = Some("pyramid"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cheops.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/cheops.htm")
  ),
  layout = "swf|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemoveConsecutiveOrEqualRankPairs,
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(canMoveFrom = FoundationCanMoveFrom.Never, visible = false, autoMoveCards = true)),
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)

package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Apophis extends GameRules(
  id = "apophis",
  completed = false,
  title = "Apophis",
  like = Some("pyramid"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/apophis.htm")),
  layout = "swf|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules(numPiles = 3)),
  foundations = IndexedSeq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  pyramids = IndexedSeq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)

package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Triangle extends GameRules(
  id = "triangle",
  completed = true,
  title = "Triangle",
  related = Seq("eleventriangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triangle.htm")),
  layout = "::swf|p",
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
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
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

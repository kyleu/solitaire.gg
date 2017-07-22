package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object ElevenTriangle extends GameRules(
  id = "eleventriangle",
  completed = true,
  title = "Eleven Triangle",
  like = Some("triangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eleven_triangle.htm")),
  layout = "s:wf|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQPairOrKPair,
  stock = Some(StockRules(dealTo = StockDealTo.WasteOrPairManually, maximumDeals = Some(1))),
  waste = Some(WasteRules(numPiles = 3)),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, canMoveFrom = FoundationCanMoveFrom.Never, visible = false, autoMoveCards = true)),
  pyramids = IndexedSeq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)

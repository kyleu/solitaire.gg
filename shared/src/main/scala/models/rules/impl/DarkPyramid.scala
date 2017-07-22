package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object DarkPyramid extends GameRules(
  id = "darkpyramid",
  completed = false,
  title = "Dark Pyramid",
  like = Some("pyramid"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Thirteen.html.en")),
  layout = "swf|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(canMoveFrom = FoundationCanMoveFrom.Never, visible = false, autoMoveCards = true)),
  pyramids = IndexedSeq(PyramidRules(
    cardsFaceDown = PyramidFaceDownCards.AllButLastRow,
    rankMatchRuleForBuilding = RankMatchRule.Down,
    rankMatchRuleForMovingStacks = RankMatchRule.Down,
    mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
    mayMoveToEmptyFrom = PileSet.Behavior.wtpf
  ))
)

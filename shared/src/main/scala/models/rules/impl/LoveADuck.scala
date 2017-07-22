package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object LoveADuck extends GameRules(
  id = "loveaduck",
  completed = false,
  title = "Love a Duck",
  layout = "f|p",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  pyramids = IndexedSeq(
    PyramidRules(
      name = "Tableau",
      height = 5,
      suitMatchRuleForBuilding = SuitMatchRule.AlternatingColors,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

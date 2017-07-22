package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Sarlacc extends GameRules(
  id = "sarlacc",
  completed = true,
  title = "Sarlacc",
  layout = ":f|c|:p",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  cells = Some(CellRules(numPiles = 6)),
  pyramids = IndexedSeq(
    PyramidRules(
      name = "Tableau",
      height = 5,
      suitMatchRuleForBuilding = SuitMatchRule.AlternatingColors,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf,
      emptyFilledWith = FillEmptyWith.Any
    )
  )
)

package models.rules.impl

import models.rules._

object Sarlacc extends GameRules(
  id = "sarlacc",
  completed = true,
  title = "Sarlacc",
  layout = ":f|c|:p",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  cells = Some(CellRules(numPiles = 6)),
  pyramids = Seq(
    PyramidRules(
      name = "Tableau",
      height = 5,
      suitMatchRuleForBuilding = SuitMatchRule.AlternatingColors,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = FillEmptyWith.Any
    )
  )
)

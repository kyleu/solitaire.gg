package models.rules.impl

import models.rules._

object LoveADuck extends GameRules(
  id = "loveaduck",
  completed = false,
  title = "Love a Duck",
  layout = "f|p",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      name = "Tableau",
      height = 5,
      suitMatchRuleForBuilding = SuitMatchRule.AlternatingColors,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

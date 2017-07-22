package models.rules.impl

import models.rules._

object LuckierThirteen extends GameRules(
  id = "luckierthirteen",
  completed = true,
  title = "Luckier Thirteen",
  aka = Map("thirteenbyzero" -> "Thirteen by Zero"),
  layout = "::::.f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)

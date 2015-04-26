package models.game.rules.custom

import models.game.rules._

object Nestor extends GameRules(
  id = "nestor",
  title = "Nestor",
  description = "...",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,

  foundations = Seq(
    FoundationRules(
      numPiles = 1,
      offscreen = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.None
    )
  ),
  reserves = Some(
    ReserveRules(
      numPiles = 4
    )
  )
)

package models.rules.impl

import models.rules._

object Nestor extends GameRules(
  id = "nestor",
  completed = true,
  title = "Nestor",
  layout = "t|::tf",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,

  foundations = IndexedSeq(FoundationRules(visible = false)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      uniqueRanks = true,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)

package models.rules.impl

import models.rules._

object Nestor extends GameRules(
  id = "nestor",
  completed = true,
  custom = true,
  title = "Nestor",
  description = """
    Discard any pair of cards of the same rank, regardless of suit (for example, two Aces, two Fives, etc.).
    Only the top cards are available for play. Spaces can't be filled.
  """,
  layout = Some("t|::tf"),
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,

  foundations = Seq(
    FoundationRules(
      numPiles = 1,
      visible = false
    )
  ),
  tableaus = Seq(
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

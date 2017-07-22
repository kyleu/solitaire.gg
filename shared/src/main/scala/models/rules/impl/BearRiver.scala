package models.rules.impl

import models.card.Rank
import models.rules._

object BearRiver extends GameRules(
  id = "bearriver",
  completed = true,
  title = "Bear River",
  layout = ":::::.f|t|::::::t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 1)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      maxCards = 3
    ),
    TableauRules(
      name = "Hole",
      setNumber = 1,
      numPiles = 3,
      initialCards = InitialCards.Count(2),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 3
    )
  )
)

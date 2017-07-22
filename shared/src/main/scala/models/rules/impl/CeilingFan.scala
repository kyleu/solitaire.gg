package models.rules.impl

import models.rules._

object CeilingFan extends GameRules(
  id = "ceilingfan",
  completed = true,
  title = "Ceiling Fan",
  like = Some("fan"),
  layout = ".::f|2t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

package models.rules.impl

import models.rules._

object PatientPairs extends GameRules(
  id = "patientpairs",
  completed = true,
  title = "Patient Pairs",
  like = Some("simplepairs"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/patient_pairs.htm")),
  layout = "f|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)

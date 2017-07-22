package models.rules.impl

import models.rules._

object FarmersWife extends GameRules(
  id = "farmerswife",
  completed = false,
  title = "Farmer's Wife",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/farmers_wife.htm")),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.DifferentSuits,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUU",
        "DDDUU",
        "DDDUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

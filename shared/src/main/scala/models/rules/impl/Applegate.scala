package models.rules.impl

import models.rules._

object Applegate extends GameRules(
  id = "applegate",
  completed = true,
  title = "Applegate",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/applegate.htm")),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU",
        "DDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

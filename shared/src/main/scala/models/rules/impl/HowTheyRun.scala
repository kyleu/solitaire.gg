package models.rules.impl

import models.rules._

object HowTheyRun extends GameRules(
  id = "howtheyrun",
  completed = true,
  title = "How They Run",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/how_they_run.htm")),
  layout = "f::c|t",
  foundations = Seq(FoundationRules(numPiles = 4, moveCompleteSequencesOnly = true)),
  tableaus = Seq(
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
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules(numPiles = 2, initialCards = 2))
)

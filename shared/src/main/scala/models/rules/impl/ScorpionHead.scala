package models.rules.impl

import models.rules._

object ScorpionHead extends GameRules(
  id = "scorpionhead",
  completed = false,
  title = "Scorpion Head",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/scorpion_head.htm")),
  layout = "f|c|t",
  foundations = Seq(FoundationRules(numPiles = 4, moveCompleteSequencesOnly = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "DDDDUUU",
        "DDDDUUU",
        "DDDDUUU",
        "DDDDUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)

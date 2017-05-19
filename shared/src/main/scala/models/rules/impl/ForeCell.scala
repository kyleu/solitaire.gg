package models.rules.impl

import models.rules._

object ForeCell extends GameRules(
  id = "forecell",
  completed = true,
  title = "ForeCell",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forecell.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Forecell.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/forecell.html")
  ),
  layout = "f:c|.t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules(initialCards = 4))
)

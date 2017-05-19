package models.rules.impl

import models.rules._

object Bath extends GameRules(
  id = "bath",
  completed = false,
  title = "Bath",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bath.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bath.html")
  ),
  layout = "f|c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UU",
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules(numPiles = 2))
)

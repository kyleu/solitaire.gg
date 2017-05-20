package models.rules.impl

import models.rules._

object Tuxedo extends GameRules(
  id = "tuxedo",
  completed = true,
  title = "Tuxedo",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/tuxedo.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/tuxedo.htm")
  ),
  layout = ":.f|c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules(numPiles = 7))
)

package models.rules.impl

import models.rules._

object ThreeCell extends GameRules(
  id = "threecell",
  completed = true,
  title = "ThreeCell",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/three_cells.htm"),
    Link("Swoop Software", "www.swoopsoftware.com/solitaire_rules/threecell.html")
  ),
  layout = "f:c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 3))
)

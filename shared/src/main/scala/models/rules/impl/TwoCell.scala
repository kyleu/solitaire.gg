package models.rules.impl

import models.rules._

object TwoCell extends GameRules(
  id = "twocell",
  completed = true,
  title = "Two Cell",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/two_cells.htm")),
  layout = "c::f|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 2))
)

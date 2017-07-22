package models.rules.impl

import models.rules._

object FourByTen extends GameRules(
  id = "fourbyten",
  completed = false,
  title = "Four by Ten",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/four_by_ten.htm")),
  layout = "f|c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(13),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 10))
)

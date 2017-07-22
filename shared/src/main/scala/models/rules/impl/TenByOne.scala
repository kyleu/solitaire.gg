package models.rules.impl

import models.rules._

object TenByOne extends GameRules(
  id = "tenbyone",
  completed = false,
  title = "Ten By One",
  like = Some("vineyard"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ten_by_one.htm")),
  layout = "f|c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 1))
)

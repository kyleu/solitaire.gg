package models.rules.impl

import models.rules._

object Winery extends GameRules(
  id = "winery",
  completed = true,
  title = "Winery",
  like = Some("vineyard"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/winery.htm")),
  layout = "f::c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 10,
    initialCards = InitialCards.RestOfDeck,
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForMovingStacks = SuitMatchRule.None
  )),
  cells = Some(CellRules(numPiles = 2, initialCards = 2))
)

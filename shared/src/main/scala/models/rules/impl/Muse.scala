package models.rules.impl

import models.rules._

object Muse extends GameRules(
  id = "muse",
  completed = true,
  title = "Muse",
  like = Some("kingalbert"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/muse.htm")),
  layout = "::.f|:c|t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 9, cardsFaceDown = TableauFaceDownCards.Count(0))),
  cells = Some(CellRules(numPiles = 7, initialCards = 7))
)

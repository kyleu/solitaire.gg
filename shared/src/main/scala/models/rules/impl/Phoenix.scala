package models.rules.impl

import models.rules._

object Phoenix extends GameRules(
  id = "phoenix",
  completed = true,
  title = "Phoenix",
  like = Some("arizona"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/phoenix.htm")),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)

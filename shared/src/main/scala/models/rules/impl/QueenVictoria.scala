package models.rules.impl

import models.rules._

object QueenVictoria extends GameRules(
  id = "queenvictoria",
  completed = true,
  title = "Queen Victoria",
  like = Some("kingalbert"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/queen_victoria.htm")),
  layout = "w.f|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)

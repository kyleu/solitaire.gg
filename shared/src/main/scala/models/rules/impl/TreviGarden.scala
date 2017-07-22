package models.rules.impl

import models.rules._

object TreviGarden extends GameRules(
  id = "trevigarden",
  completed = false,
  title = "Trevi Garden",
  like = Some("stonewall"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trevi_garden.htm")),
  layout = "wf|c|t",
  waste = Some(WasteRules(name = "Fountain")),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Garden",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.OddNumbered
    )
  ),
  cells = Some(CellRules(numPiles = 2, initialCards = 2))
)

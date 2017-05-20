package models.rules.impl

import models.rules._

object NorthwestTerritory extends GameRules(
  id = "northwestterritory",
  completed = false,
  title = "Northwest Territory",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/northwest_territory.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/northwest_territory.html")
  ),
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
      numPiles = 8,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)

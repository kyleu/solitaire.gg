package models.rules.impl

import models.rules._

object KlondikeTerritory extends GameRules(
  id = "klondiketerritory",
  completed = false,
  title = "Klondike Territory",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/klondike_territory.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/klondike_territory.html")
  ),
  layout = "wf|t",
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
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)

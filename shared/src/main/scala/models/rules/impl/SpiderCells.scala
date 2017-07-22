package models.rules.impl

import models.rules._

object SpiderCells extends GameRules(
  id = "spidercells",
  completed = false,
  title = "SpiderCells",
  like = Some("freecell"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spidercells.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/spidercell.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/spidercells.htm")
  ),
  layout = "|c|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  ),
  cells = Some(CellRules())
)

package models.rules.impl

import models.rules._

object GreatTriangle extends GameRules(
  id = "greattriangle",
  completed = false,
  title = "Great Triangle",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/great_triangle.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)

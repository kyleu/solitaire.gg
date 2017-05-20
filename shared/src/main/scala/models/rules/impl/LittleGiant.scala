package models.rules.impl

import models.rules._

object LittleGiant extends GameRules(
  id = "littlegiant",
  completed = false,
  title = "Little Giant",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_giant.htm")),
  layout = "swf|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)

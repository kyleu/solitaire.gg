package models.rules.impl

import models.rules._

object EternalTriangle extends GameRules(
  id = "eternaltriangle",
  completed = false,
  title = "Eternal Triangle",
  related = Seq("hypotenuse", "tripletriangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eternal_triangle.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

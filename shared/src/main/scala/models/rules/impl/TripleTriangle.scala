package models.rules.impl

import models.rules._

object TripleTriangle extends GameRules(
  id = "tripletriangle",
  completed = true,
  title = "Triple Triangle",
  like = Some("eternaltriangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_triangle.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

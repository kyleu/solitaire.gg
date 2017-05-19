package models.rules.impl

import models.rules._

object Hypotenuse extends GameRules(
  id = "hypotenuse",
  completed = true,
  title = "Hypotenuse",
  like = Some("eternaltriangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/hypotenuse.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDDDU",
        "DDDDDDDDU",
        "DDDDDDDU",
        "DDDDDDU",
        "DDDDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

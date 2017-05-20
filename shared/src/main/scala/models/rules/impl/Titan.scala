package models.rules.impl

import models.rules._

object Titan extends GameRules(
  id = "titan",
  completed = true,
  title = "Titan",
  like = Some("giant"),
  links = Seq(Link("Solsuite Solitaire", "www.solsuite.com/games/titan.htm")),
  layout = "sf|.t",
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
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)

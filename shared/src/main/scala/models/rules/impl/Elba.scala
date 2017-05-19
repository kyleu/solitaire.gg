package models.rules.impl

import models.rules._

object Elba extends GameRules(
  id = "elba",
  completed = false,
  title = "Elba",
  like = Some("fortythieves"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/elba.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 10, initialCards = InitialCards.Count(5), emptyFilledWith = FillEmptyWith.HighRank))
)

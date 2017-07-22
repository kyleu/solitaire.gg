package models.rules.impl

import models.rules._

object Brazilian extends GameRules(
  id = "brazilian",
  completed = true,
  title = "Brazilian",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/brazilian_patience.htm")),
  layout = "s.f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(numPiles = 10, emptyFilledWith = FillEmptyWith.HighRank))
)

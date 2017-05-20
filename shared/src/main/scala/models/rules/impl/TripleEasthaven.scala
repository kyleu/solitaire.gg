package models.rules.impl

import models.rules._

object TripleEasthaven extends GameRules(
  id = "tripleeasthaven",
  completed = true,
  title = "Triple Easthaven",
  like = Some("easthaven"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_easthaven.htm")),
  layout = "sf|.t",
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
      numPiles = 12,
      initialCards = InitialCards.Count(3)
    )
  )
)

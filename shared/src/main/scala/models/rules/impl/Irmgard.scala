package models.rules.impl

import models.rules._

object Irmgard extends GameRules(
  id = "irmgard",
  completed = true,
  title = "Irmgard",
  like = Some("gypsy"),
  links = Seq(Link("PySol", "pysolfc.sourceforge.net/doc/rules/irmgard.html")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(3),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

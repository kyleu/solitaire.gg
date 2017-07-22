package models.rules.impl

import models.rules._

object QuadrupleKlondike extends GameRules(
  id = "quadrupleklondike",
  completed = true,
  title = "Quadruple Klondike",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_klondike.htm")),
  layout = "swf|:.t",
  deckOptions = DeckOptions(numDecks = 4),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 16,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

package models.rules.impl

import models.rules._

object TripleKlondike extends GameRules(
  id = "tripleklondike",
  completed = true,
  title = "Triple Klondike",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_klondike.htm")),
  layout = "swf|:t",
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

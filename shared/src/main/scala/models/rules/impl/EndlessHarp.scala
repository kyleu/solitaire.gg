package models.rules.impl

import models.rules._

object EndlessHarp extends GameRules(
  id = "endlessharp",
  completed = true,
  title = "Endless Harp",
  like = Some("klondike"),
  related = Seq("bigharp"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/endless_harp.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(numPiles = 10, emptyFilledWith = FillEmptyWith.HighRank))
)

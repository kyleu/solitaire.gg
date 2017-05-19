package models.rules.impl

import models.rules._

object BigHarp extends GameRules(
  id = "bigharp",
  completed = false,
  title = "Big Harp",
  like = Some("endlessharp"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_harp.htm"),
    Link("Lena Games", "www.lenagames.com/bp_files/rul/big-harp.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(3), cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 10, emptyFilledWith = FillEmptyWith.HighRank))
)

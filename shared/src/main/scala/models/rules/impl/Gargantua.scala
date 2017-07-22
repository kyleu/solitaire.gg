package models.rules.impl

import models.rules._

object Gargantua extends GameRules(
  id = "gargantua",
  completed = true,
  title = "Gargantua",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Gargantua_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gargantua.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/gargantua.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/gargantua.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/gargantua.htm")
  ),
  layout = "swf|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

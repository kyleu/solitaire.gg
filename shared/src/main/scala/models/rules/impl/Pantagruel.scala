package models.rules.impl

import models.rules._

object Pantagruel extends GameRules(
  id = "pantagruel",
  completed = true,
  title = "Pantagruel",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pantagruel.htm")),
  layout = "swf|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      numPiles = 9
    )
  )
)

package models.rules.impl

import models.rules._

object DoubleGoldRush extends GameRules(
  id = "doublegoldrush",
  completed = true,
  title = "Double Gold Rush",
  like = Some("goldrush"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_gold_rush.htm")),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(3), cardsDealt = StockCardsDealt.FewerEachTime)),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(numPiles = 10, emptyFilledWith = FillEmptyWith.HighRank))
)

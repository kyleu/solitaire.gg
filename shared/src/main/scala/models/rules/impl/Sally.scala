package models.rules.impl

import models.card.Rank
import models.rules._

object Sally extends GameRules(
  id = "sally",
  completed = false,
  title = "Sally",
  like = Some("doubleklondike"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sally.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

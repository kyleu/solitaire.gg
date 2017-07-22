package models.rules.impl

import models.rules._

object DoubleEasthaven extends GameRules(
  id = "doubleeasthaven",
  completed = true,
  title = "Double Easthaven",
  like = Some("easthaven"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_easthaven.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_easthaven.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/double_easthaven.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/double-easthaven.asp")
  ),
  layout = "sf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(numPiles = 8, initialCards = InitialCards.Count(3)))
)

package models.rules.impl

import models.rules._

object Alternations extends GameRules(
  id = "alternations",
  completed = true,
  title = "Alternations",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/alternations.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Alternation_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/alternation.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/alternations.htm"),
    Link("Solitaire City", "www.solitairecity.com/Alternations.shtml")
  ),
  layout = "swf|::t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(initialCards = InitialCards.Count(7), cardsFaceDown = TableauFaceDownCards.EvenNumbered))
)

package models.rules.impl

import models.rules._

object Carlton extends GameRules(
  id = "carlton",
  completed = true,
  title = "Carlton",
  related = Seq("steve"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/carlton.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/carlton.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/carlton.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/carlton.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/carlton.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Carlton.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/carlton.htm")
  ),
  layout = "sf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 8))
)

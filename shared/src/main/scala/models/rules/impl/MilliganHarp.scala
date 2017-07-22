package models.rules.impl

import models.rules._

object MilliganHarp extends GameRules(
  id = "milliganharp",
  completed = true,
  title = "Milligan Harp",
  related = Seq("milliganyukon"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/milligan_harp.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/milligan_harp.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/milligan_harp.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/MilliganHarp.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/milliganharp.html")
  ),
  layout = "sf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8
    )
  )
)

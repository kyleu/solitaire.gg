package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): milliganyukon
 */
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
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8
    )
  )
)

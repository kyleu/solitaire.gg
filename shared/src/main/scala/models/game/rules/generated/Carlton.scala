// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

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
 *   Related games (related): steve
 */
object Carlton extends GameRules(
  id = "carlton",
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
  description = "A difficult two-deck ^klondike^ variation.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
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

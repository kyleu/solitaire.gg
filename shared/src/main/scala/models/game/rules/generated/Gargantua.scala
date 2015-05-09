// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Gargantua extends GameRules(
  id = "gargantua",
  title = "Gargantua",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Gargantua_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gargantua.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/gargantua.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/gargantua.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/gargantua.htm")
  ),
  description = "A two-deck version of ^klondike^ invented by Albert Morehead and Geoffrey Mott-Smith. You get two passes through the deck, dealing" +
    " cards one at a time.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      emptyFilledWith = FillEmptyWith.Kings
    )
  )
)

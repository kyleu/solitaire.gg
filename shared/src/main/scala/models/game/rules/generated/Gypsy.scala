// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): irmgard
 */
object Gypsy extends GameRules(
  id = "gypsy",
  title = "Gypsy",
  related = Seq("irmgard"),
  links = Seq(
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/gypsy.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Gypsy.html.en"),
    Link("KPatience", "docs.kde.org/stable/en/kdegames/kpat/rules-specific.html#gypsy")
  ),
  description = "A cross between ^spider^ and ^klondike^.",
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
      numPiles = 8,
      initialCards = InitialCards.Count(3)
    )
  )
)

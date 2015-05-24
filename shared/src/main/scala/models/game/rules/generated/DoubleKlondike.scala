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
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Maximum deals from stock (maxdeals): 0
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): sally, suittriangle
 */
object DoubleKlondike extends GameRules(
  id = "doubleklondike",
  title = "Double Klondike",
  related = Seq("sally", "suittriangle"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_klondike.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/double_klondike.htm"),
    Link("Dan Fletcher's How To Play", "www.solitairecentral.com/articles/HowToPlayDoubleKlondike.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Jumbo.html.en")
  ),
  description = "A two-deck version of ^klondike^. This game is almost always winnable.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
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

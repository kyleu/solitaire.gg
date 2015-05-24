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
 *   Maximum deals from stock (maxdeals): 4 (4)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): tripleharp
 */
object Harp extends GameRules(
  id = "harp",
  title = "Harp",
  related = Seq("tripleharp"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/harp.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/harp.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/harp.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Harp.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/harp.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/harp.html")
  ),
  description = "A two-deck ^klondike^ variation.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(4)
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

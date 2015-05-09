// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 2
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Similar to (like): maria
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): midshipman
 */
object Midshipman extends GameRules(
  id = "midshipman",
  title = "Midshipman",
  like = Some("maria"),
  related = Seq("midshipman"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/midshipman.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/midshipman.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/midshipman.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/midshipman.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/midshipman.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Midshipman.htm")
  ),
  description = "A slightly easier variation of ^maria^ where we build by different suits instead of alternate colors and where some cards start ou" +
    "t face down.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(2),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)

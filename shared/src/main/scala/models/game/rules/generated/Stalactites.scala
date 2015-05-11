// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 2
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Foundation initial cards (F0d): -1
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Low card (lowpip): -1 (.)
 *   Touch interface function (touchfunc): 0x2|0x20
 */
object Stalactites extends GameRules(
  id = "stalactites",
  title = "Stalactites",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Stalactites_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stalactites.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/stalactites.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Stalactites.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/stalactites.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/stalactites.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Stalactites.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/stalactites.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/stalactites.html")
  ),
  description = "This suitless game with no building requires you to clear the tableau with only two cells you help you.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 4,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  )
)

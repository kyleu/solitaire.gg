// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Empty tableau is filled from (T0fo): BIT_WASTE
 *   Tableau piles (T0n): 20
 *   Tableau rank match rule for building (T0r): 0x0000
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 */
object Carpet extends GameRules(
  id = "carpet",
  title = "Carpet",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Carpet_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/carpet.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/carpet.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/carpet.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Carpet.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/carpet.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/carpet.htm")
  ),
  description = "An easy game with twenty reserve piles and no building.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 20,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToEmptyFrom = Seq("Waste")
    )
  ),
  complete = false
)


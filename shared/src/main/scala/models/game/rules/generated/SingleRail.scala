// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Related games (related): doublerail
 */
object SingleRail extends GameRules(
  id = "singlerail",
  title = "Single Rail",
  related = Seq("doublerail"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/single_rail.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/single_rail.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/single_rail.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/single-rail.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SingleRail.htm")
  ),
  description = "A one-deck version of ^doublerail^. A good little game for small screens.",
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
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)


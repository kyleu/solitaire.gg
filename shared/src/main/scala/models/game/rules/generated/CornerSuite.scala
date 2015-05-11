// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 0 (None)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Empty tableau is filled from (T0fo): 251
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 */
object CornerSuite extends GameRules(
  id = "cornersuite",
  title = "Corner Suite",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/corner_suite.htm")),
  description = "This rather easy game resembles a one deck version of ^congress^, except that the tableau starts empty.",
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
      numPiles = 9,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation")
    )
  )
)

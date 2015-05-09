// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Deal order (RDd): 0 (Rows, left to right, bottom to top)
 *   Allowed pick ups/redeals (RDn): 6 (6)
 *   Pickup order (RDp): 1 (Columns, left to right, bottom to top)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 */
object DemonFan extends GameRules(
  id = "demonfan",
  title = "Demon Fan",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/demon_fan.htm")),
  description = "A very easy game where you build down in alternate colors, and are allowed six redeals.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 6,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.RowsLeftToRightTopToBottom
    )
  )
)

// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Deal order (RDd): 0 (Rows, left to right, bottom to top)
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Pickup order (RDp): 1 (Columns, left to right, bottom to top)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau action during deal (T0dd): 1 (Move kings to stack bottoms)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): perseverancea
 *   Right mouse interface function (rightfunc): 0
 */
object PerseveranceB extends GameRules(
  id = "perseveranceb",
  title = "Perseverance B",
  like = Some("perseverancea"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/perseverance.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/perseverance.htm")
  ),
  description = "An alternate version of ^perseverancea^ where there are only two redeals, the redeal method is different, and kings are automatica" +
    "lly moved to the bottoms of their stacks.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)

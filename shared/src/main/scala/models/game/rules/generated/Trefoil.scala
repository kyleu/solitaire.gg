// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Deal order (RDd): 9 (Columns, left to right, top to bottom)
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Shuffle before redealing (RDs): 1 (Yes)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Custom initial cards (T0ds): UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU U
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): labellelucie
 */
object Trefoil extends GameRules(
  id = "trefoil",
  title = "Trefoil",
  like = Some("labellelucie"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trefoil.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Trefoil.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/trefoil.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/trefoil.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/trefoil.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Trefoil.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/trefoil.html")
  ),
  description = "A slightly easier variation of ^labellelucie^ where the aces start on the foundation and there are fewer tableau columns.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      dealOrder = DealOrder.ColumnsLeftToRightTopToBottom
    )
  )
)

// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 3 (3 cards)
 *   Number of cells (C0n): 3
 *   Deal order (RDd): 0|0|0
 *   *RDdo (RDdo): BIT_TABLEAU|BIT_CELL
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Pickup order (RDp): 1|0|0
 *   *RDpo (RDpo): BIT_TABLEAU|BIT_CELL
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Tableau piles (T0n): 7
 *   Tableau rank match rule for building (T0r): 0x0020|0x0080
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 */
object SevenBySeven extends GameRules(
  id = "sevenbyseven",
  title = "Seven by Seven",
  description = "This danish game with a seven-by-seven tableau and three cells allows two redeals.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 3,
      initialCards = 3
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.RowsLeftToRightTopToBottom
    )
  ),
  complete = false
)


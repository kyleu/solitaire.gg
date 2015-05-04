// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Deal order (RDd): 0|0|0
 *   Allowed pick ups/redeals (RDn): 1 (1)
 *   Pickup order (RDp): 1|0|0
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUUU UUUUUUUU UUUUUUUU UUUUUUU UUUUUU UUUUU UUUU UUU UU U
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): somerset
 *   Related games (related): morehead, usk
 */
object Usk extends GameRules(
  id = "usk",
  title = "Usk",
  like = Some("somerset"),
  related = Seq("morehead", "usk"),
  description = "A ^klondike^ variant without stock or waste. Unlike ^somerset^, moves of stacks are allowed and there is a redeal, but spaces can " +
  "only be filled by kings.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 1,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.RowsLeftToRightTopToBottom
    )
  ),
  complete = false
)


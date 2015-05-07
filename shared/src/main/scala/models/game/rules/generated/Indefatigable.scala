// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Deal order (RDd): 0|0|0
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Pickup order (RDp): 1|0|0
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 12
 *   Tableau rank match rule for building (T0r): 0x0020|0x0080
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0x0
 *   Similar to (like): cruel
 *   Related games (related): royalfamily
 *   Right mouse interface function (rightfunc): 0x2
 */
object Indefatigable extends GameRules(
  id = "indefatigable",
  title = "Indefatigable",
  like = Some("cruel"),
  related = Seq("royalfamily"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/indefatigable.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Indefatigable.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/the_indefatigable.php"),
    Link("Jan Wolter's Experiments", "/article/indefatigable.html")
  ),
  description = "This variation of ^royalfamily^ is basically the same, but the foundations build up from ace, and it is made even easier by a extr" +
    "a redeal.",
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
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
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


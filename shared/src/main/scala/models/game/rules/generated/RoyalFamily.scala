// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation low rank (F0b): 22 (Deck's high card)
 *   Foundation initial cards (F0d): -1
 *   Foundation rank match rule (F0r): 32 (Build down)
 *   Deal order (RDd): 0 (Rows, left to right, bottom to top)
 *   Allowed pick ups/redeals (RDn): 1 (1)
 *   Pickup order (RDp): 1 (Columns, left to right, bottom to top)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 12
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0
 *   Similar to (like): indefatigable
 *   Right mouse interface function (rightfunc): 2
 */
object RoyalFamily extends GameRules(
  id = "royalfamily",
  title = "Royal Family",
  like = Some("indefatigable"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/royal_family.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/royal-family.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/royal_family.php"),
    Link("Jan Wolter's Experiments", "/article/indefatigable.html")
  ),
  description = "This game allows you to build up and down and fill spaces with any card, which makes the game so easy that you often don't need th" +
    "e redeal that you are allowed.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
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
      redealsAllowed = 1,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.RowsLeftToRightTopToBottom
    )
  )
)

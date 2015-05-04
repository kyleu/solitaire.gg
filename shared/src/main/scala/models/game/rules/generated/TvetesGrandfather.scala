// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Deal order (RDd): 16
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Pickup order (RDp): 1|0|0
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): D DDUUUUU DDDDUUUUU DDDDDDUUUUU DDDDDUUUUU DDDUUUUU DUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 */
object TvetesGrandfather extends GameRules(
  id = "tvetesgrandfather",
  title = "Tvete's Grandfather",
  description = "Paul Olav Tvete learned this game from his grandfather and included it in <a target=\"_blank\" href=\"\"http\"://www.kde.org/appli" +
  "cations/games/kpatience/\">KPatience</a>. It is a difficult game with an unusual tableau, ^yukon^-style stack moves, and two redea" +
  "ls.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "D",
        "DDUUUUU",
        "DDDDUUUUU",
        "DDDDDDUUUUU",
        "DDDDDUUUUU",
        "DDDUUUUU",
        "DUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.ColumnsLeftToRightTopToBottom
    )
  ),
  complete = false
)


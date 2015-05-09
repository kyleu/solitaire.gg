// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Reserve initial cards (R0d): 7
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 4
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 */
object Castile extends GameRules(
  id = "castile",
  title = "Castile",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castile.htm")),
  description = "An open variant of ^bristol^ invented by Thomas Warfield.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = 7,
      cardsFaceDown = 0
    )
  )
)

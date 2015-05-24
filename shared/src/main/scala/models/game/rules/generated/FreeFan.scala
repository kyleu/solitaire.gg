// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 2
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): fan
 *   Related games (related): luckyfan
 */
object FreeFan extends GameRules(
  id = "freefan",
  title = "FreeFan",
  like = Some("fan"),
  related = Seq("luckyfan"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/free_fan.htm")),
  description = "An easy variation of ^fan^ with cells. Invented by Gregg Seelhoff.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  )
)

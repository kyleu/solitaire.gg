// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Maximum cards for foundation (F0m): 26
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   Foundation suit match rule (F0s): 4 (In alternating colors)
 *   Initial card restriction (F0u): 1 (Unique colors)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object HalfCell extends GameRules(
  id = "halfcell",
  title = "HalfCell",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/halfcell.htm")),
  description = "^freecell^ with only two foundation piles.",
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.AlternatingColors,
      maxCards = 26,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)

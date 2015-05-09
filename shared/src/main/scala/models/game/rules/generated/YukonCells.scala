// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U DUUUUU DDUUUUU DDDUUUUU DDDDUUUUU DDDDDUUUUU DDDDDDUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): yukon
 */
object YukonCells extends GameRules(
  id = "yukoncells",
  title = "Yukon Cells",
  like = Some("yukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukon_cells.htm")),
  description = "A variation of ^yukon^ simplified by the addition of two cells.",
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
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  )
)

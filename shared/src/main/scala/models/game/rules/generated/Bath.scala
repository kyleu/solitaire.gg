// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U UU UUU UUUU UUUUU UUUUUU UUUUUUU UUUUUUUU UUUUUUUU UUUUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Bath extends GameRules(
  id = "bath",
  title = "Bath",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bath.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bath.html")
  ),
  description = "A ^freecell^ variant where spaces can only be filled by kings and there are only two cells.",
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
        "U",
        "UU",
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  )
)

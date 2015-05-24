// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 7
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUUU UUUUUUU UUUUUUU UUUUUUUU UUUUUUU UUUUUUU UUUUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 */
object Tuxedo extends GameRules(
  id = "tuxedo",
  title = "Tuxedo",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/tuxedo.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/tuxedo.htm")
  ),
  description = "An easier variant of ^penguin^ where all cards start on the tableau.",
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
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 7
    )
  )
)

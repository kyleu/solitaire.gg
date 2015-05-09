// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 4 (4 cards)
 *   Number of cells (C0n): 8
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): eighton
 *   Enable super moves, whatever those are (supermoves): 1
 */
object EightOff extends GameRules(
  id = "eightoff",
  title = "Eight Off",
  related = Seq("eighton"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eight_off.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/EightOff.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/eight_off.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Eight_Off.html.en"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/eight_off.htm")
  ),
  description = "A ^freecell^ variation with more cells, but where you can only build down in the same suit",
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
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 8,
      initialCards = 4
    )
  )
)

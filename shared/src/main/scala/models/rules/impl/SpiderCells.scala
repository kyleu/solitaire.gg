// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Foundation Sets (Fn): 0
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Enable super moves, whatever those are (supermoves): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 *   *vsuit (vsuit): 4
 */
object SpiderCells extends GameRules(
  id = "spidercells",
  title = "SpiderCells",
  like = Some("freecell"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spidercells.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/spidercell.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/spidercells.htm")
  ),
  description = "A ^freecell^ variant where you need to build complete alternating color sequences on the tableau.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  ),
  cells = Some(CellRules())
)

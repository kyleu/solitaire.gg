// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of ephemeral cells (C0e): 1 (1 cell)
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecell
 *   Enable super moves, whatever those are (supermoves): 1
 */
object EphemeralFreeCell extends GameRules(
  id = "ephemeralfreecell",
  title = "Ephemeral FreeCell",
  like = Some("freecell"),
  links = Seq(Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html#Ephemeral")),
  description = "Ephemeral FreeCell is like standard ^freecell^, except that one of the cells will vanish after it's first use. Michael Keller inve" +
    "nted this idea. You can experiment with different numbers of ephemeral cells.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
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
  cells = Some(
    CellRules(
      numEphemeral = 1
    )
  )
)

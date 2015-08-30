// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 1
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): streetsandalleys
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Stronghold extends GameRules(
  id = "stronghold",
  completed = false,
  title = "Stronghold",
  like = Some("streetsandalleys"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stronghold.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Stronghold.htm")
  ),
  description = "A variation of ^beleagueredcastle^ with one ^freecell^ style cell added.",
  layout = Some("f|c|t"),
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
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 1
    )
  )
)
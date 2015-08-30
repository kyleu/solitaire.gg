// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 4 (4 cards)
 *   Number of cells (C0n): 4
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 10 (10 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Repair extends GameRules(
  id = "repair",
  completed = false,
  title = "Repair",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/repair.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Repair.htm")
  ),
  description = "A two-deck version of ^freecell^.",
  layout = "f|c|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(10),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      initialCards = 4
    )
  )
)
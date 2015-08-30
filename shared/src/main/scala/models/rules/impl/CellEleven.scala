// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 2 (2 cards)
 *   Number of cells (C0n): 11
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUUUUUUUU UUUUUUUUUUUUU UUUUUUUUUUUUU UUUUUUUUUUUUU UUUUUUUUUUUUU UUUUUUUUUU...
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): triplefreecell
 *   Number of decks (ndecks): 3 (3 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object CellEleven extends GameRules(
  id = "celleleven",
  completed = false,
  title = "Cell Eleven",
  like = Some("triplefreecell"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cell_11.htm")),
  description = "A three-deck version of ^freecell^.",
  layout = Some("f|c|t"),
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 11,
      initialCards = 2
    )
  )
)
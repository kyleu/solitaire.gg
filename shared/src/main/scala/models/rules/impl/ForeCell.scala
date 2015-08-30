// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 4 (4 cards)
 *   Number of cells (C0n): 4
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object ForeCell extends GameRules(
  id = "forecell",
  completed = true,
  title = "ForeCell",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forecell.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Forecell.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/forecell.html")
  ),
  description = "A Swedish predecessor to ^freecell^, originally one of many games called \"Napolean at St. Helena\". The initial layout is a bit d" +
    "ifferent from FreeCell, and spaces can only be filled by Kings.",
  layout = "f:c|.t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(
    CellRules(
      initialCards = 4
    )
  )
)
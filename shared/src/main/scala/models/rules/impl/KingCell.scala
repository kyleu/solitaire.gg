// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object KingCell extends GameRules(
  id = "kingcell",
  title = "KingCell",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingcell.htm")),
  description = "A variation of ^freecell^ where we build down regardless of suit instead of by alternate color, and only kings may be played to em" +
    "pty tableau spaces",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)

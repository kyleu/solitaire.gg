// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 8 (8 cards)
 *   Number of cells (C0n): 8
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Piles with low cards at bottom (T0dc): 4 (4 columns)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): eightoff
 *   Enable super moves, whatever those are (supermoves): 1
 */
object EightOn extends GameRules(
  id = "eighton",
  title = "Eight On",
  like = Some("eightoff"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eight_on.htm")),
  description = "A harder variation of ^eightoff^ where the aces start on the bottoms of the piles. Invented by Thomas Warfield.",
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
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      pilesWithLowCardsAtBottom = 4
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 8,
      initialCards = 8
    )
  )
)

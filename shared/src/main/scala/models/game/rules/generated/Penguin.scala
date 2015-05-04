// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 7
 *   Foundation initial cards (F0d): 3 (3 cards)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Piles with low cards at bottom (T0dc): 1 (1 columns)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Low card (lowpip): -2 (?)
 *   Related games (related): opus
 */
object Penguin extends GameRules(
  id = "penguin",
  title = "Penguin",
  related = Seq("opus"),
  description = "A satisfying game with seven cells developed by David Parlett where one of the cards you need to start the foundation is always bu" +
  "ried at the bottom of the first tableau pile.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 3,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      pilesWithLowCardsAtBottom = 1
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 7
    )
  ),
  complete = false
)


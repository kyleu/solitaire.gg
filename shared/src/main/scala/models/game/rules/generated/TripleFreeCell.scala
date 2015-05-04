// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 10
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 12 (12 cards)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 3 (3 decks)
 *   Related games (related): celleleven, freecellthreedeck
 *   Enable super moves, whatever those are (supermoves): 1
 */
object TripleFreeCell extends GameRules(
  id = "triplefreecell",
  title = "Triple FreeCell",
  description = "Thomas Warfield's three-deck version of ^freecell^.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 10
    )
  ),
  complete = false
)


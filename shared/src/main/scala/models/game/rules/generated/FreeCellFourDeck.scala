// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 14
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 14
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): freecellthreedeck
 *   Number of decks (ndecks): 4 (4 decks)
 *   Related games (related): freecellfourdeck
 *   Enable super moves, whatever those are (supermoves): 1
 */
object FreeCellFourDeck extends GameRules(
  id = "freecellfourdeck",
  title = "FreeCell Four Deck",
  like = Some("freecellthreedeck"),
  related = Seq("freecellfourdeck"),
  description = "A four-deck version of ^freecell^ for those who like spending a long time solving a single deal.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 14
    )
  ),
  complete = false
)


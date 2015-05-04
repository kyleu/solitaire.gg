// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Keep foundation off-screen (F0i): true
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDDDUUUUUUU DDDDDUUUUUUU DDDDDUUUUUUU DDDDDUUUUUUU DDDDDUUUUUUU DDDDDUUUUUUU UU...
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 3 (3 decks)
 */
object TripleScorpion extends GameRules(
  id = "triplescorpion",
  title = "Triple Scorpion",
  description = "A three-deck variation of ^scorpion^ with no reserve.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Aces Foundation
 *   Foundation initial cards (F0d): 4 (4 cards)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Foundation name (F1Nm): Kings Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): 4 (4 cards)
 *   Can move cards from foundation (F1mb): true
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 16
 *   May move to non-empty tableau from (T0o): 4 (tableau)
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Similar to (like): crescent
 *   Number of decks (ndecks): 2 (2 decks)
 *   Allowed tableau rotations (nrot): 4 (4)
 *   Rotation direction (toptobot): false
 */
object CrescentFour extends GameRules(
  id = "crescentfour",
  title = "Crescent Four",
  like = Some("crescent"),
  description = "An easier variation of ^crescent^ that allows one extra rotation.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq("tableau")
    )
  ),
  special = Some(
    SpecialRules(
      shuffleBeforeRedeal = false,
      rotationsAllowed = 4,
      rotationTopToBottom = false
    )
  )
)

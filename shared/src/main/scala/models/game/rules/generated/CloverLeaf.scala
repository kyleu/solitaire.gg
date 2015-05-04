// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Red Foundation
 *   Foundation initial cards (F0d): 2 (2 cards)
 *   Number of foundation piles (F0n): 2 (2 stacks)
 *   TODO (F0u): 3
 *   Foundation name (F1Nm): Black Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): 2 (2 cards)
 *   Number of foundation piles (F1n): 2 (2 stacks)
 *   Foundation rank match rule (F1r): 0x0020
 *   TODO (F1u): 4
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 9 (Kings or aces only)
 *   Tableau piles (T0n): 16
 *   Tableau rank match rule for building (T0r): 0x0020|0x0080
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): alexanderthegreat
 *   Related games (related): alternative
 */
object CloverLeaf extends GameRules(
  id = "cloverleaf",
  title = "Clover Leaf",
  like = Some("alexanderthegreat"),
  description = "An easy game invented by Thomas Warfield where you build up or down on the tableau, two foundation piles build up, and two build d" +
  "own.",
  foundations = Seq(
    FoundationRules(
      name = "Red Foundation",
      numPiles = 2,
      initialCards = 2,
      wrapFromKingToAce = true,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Black Foundation",
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = 2,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.KingsOrAces
    )
  ),
  complete = false
)


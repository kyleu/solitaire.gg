// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Tableau
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Maximum cards per tableau (T0m): 3 (3 cards)
 *   Tableau piles (T0n): 15
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau name (T1Nm): Hole
 *   Tableau initial cards (T1d): 2 (2 cards)
 *   Empty tableau is filled with (T1f): 0 (Any card)
 *   Maximum cards per tableau (T1m): 3 (3 cards)
 *   Tableau piles (T1n): 3
 *   Tableau rank match rule for building (T1r): 160 (Build up or down)
 *   Tableau suit match rule for building (T1s): 1 (In same suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Low card (lowpip): -2 (?)
 */
object BearRiver extends GameRules(
  id = "bearriver",
  title = "Bear River",
  description = "A ^fan^ variation where you can build up and down in suit, but are limited to three cards per pile.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      maxCards = 3
    ),
    TableauRules(
      name = "Hole",
      setNumber = 1,
      numPiles = 3,
      initialCards = InitialCards.Count(2),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 3
    )
  )
)

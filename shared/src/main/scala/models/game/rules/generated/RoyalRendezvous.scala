// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Straight Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   TODO (F0u): 2
 *   Foundation name (F1Nm): Odd Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation initial cards (F1d): -1
 *   Maximum cards for foundation (F1m): 6
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 0x0100
 *   TODO (F1u): 2
 *   Foundation name (F2Nm): Even Foundation
 *   Auto-move cards to foundation (F2a): 0 (Never)
 *   Foundation low rank (F2b): 2 (2)
 *   Foundation initial cards (F2d): -1
 *   Maximum cards for foundation (F2m): 6
 *   Number of foundation piles (F2n): 4 (4 stacks)
 *   Foundation rank match rule (F2r): 0x0100
 *   TODO (F2u): 2
 *   Foundation name (F3Nm): King Foundation
 *   Auto-move cards to foundation (F3a): 0 (Never)
 *   Foundation low rank (F3b): 13
 *   Maximum cards for foundation (F3m): 1
 *   Number of foundation piles (F3n): 4 (4 stacks)
 *   TODO (F3u): 2
 *   Foundation Sets (Fn): 4
 *   Auto-fill an empty tableau from (T0af): 2|4
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object RoyalRendezvous extends GameRules(
  id = "royalrendezvous",
  title = "Royal Rendezvous",
  description = "An odd Austrian game with four foundation sets, one normal, one for evens, one for odds, and one for kings. No building on the tab" +
  "leau.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Straight Foundation",
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      name = "Odd Foundation",
      setNumber = 1,
      numPiles = 4,
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 6
    ),
    FoundationRules(
      name = "Even Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 6
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 3,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 1
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  ),
  complete = false
)


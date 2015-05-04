// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation low rank (F0b): 6 (6)
 *   Foundation initial cards (F0d): -1
 *   Maximum cards for foundation (F0m): 6
 *   Foundation rank match rule (F0r): 0x0020
 *   Auto-move cards to foundation (F1a): 1 (Whenever possible)
 *   Foundation low rank (F1b): 7 (7)
 *   Foundation initial cards (F1d): -1
 *   Maximum cards for foundation (F1m): 7
 *   Foundation Sets (Fn): 2
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Maximum cards per tableau (T0m): 1 (1 cards)
 *   Tableau piles (T0n): 9
 *   Number of decks (ndecks): 2 (2 decks)
 */
object SixesAndSevens extends GameRules(
  id = "sixesandsevens",
  title = "Sixes and Sevens",
  description = "No building on tableau, some foundation build up, some build down.",
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
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = 8,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      maxCards = 6,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCards = 8,
      wrapFromKingToAce = true,
      maxCards = 7,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces,
      maxCards = 1
    )
  ),
  complete = false
)


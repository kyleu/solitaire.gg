// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Ace Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 8 (8 stacks)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Foundation name (F1Nm): King Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Can move cards from foundation (F1mb): 1 (Always)
 *   Number of foundation piles (F1n): 8 (8 stacks)
 *   Foundation rank match rule (F1r): 0x0020
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Foundation Sets (Fn): 2
 *   Reserve initial cards (R0d): 13
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 4
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Similar to (like): acesandkings
 *   Number of decks (ndecks): 4 (4 decks)
 *   Related games (related): doubleacesandkings, aceyandkingsley, deucesandqueens, racingaces
 */
object DoubleAcesAndKings extends GameRules(
  id = "doubleacesandkings",
  title = "Double Aces and Kings",
  like = Some("acesandkings"),
  related = Seq("doubleacesandkings", "aceyandkingsley", "deucesandqueens", "racingaces"),
  description = "A four-deck version of ^acesandkings^ invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 8,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.DeckHighRank,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = 13,
      cardsFaceDown = 0
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Left Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation low rank (F0b): 5 (5)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Foundation name (F1Nm): Right Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 5 (5)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 0x0020
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Foundation Sets (Fn): 2
 *   Tableau name (T0Nm): Left Tableau
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 3
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Tableau name (T1Nm): Right Tableau
 *   Tableau initial cards (T1d): 1 (1 card)
 *   Tableau piles (T1n): 3
 *   Tableau rank match rule for building (T1r): 0x0080
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T1w): true
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of decks (ndecks): 2 (2 decks)
 *   *unused (unused): temp_hack
 */
object Penta extends GameRules(
  id = "penta",
  title = "Penta",
  description = "Another ^busyaces^ variation by Thomas Warfield, in this one, half the tabeau builds up, and half builds down.",
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
      name = "Left Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      name = "Right Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Left Tableau",
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    ),
    TableauRules(
      name = "Right Tableau",
      setNumber = 1,
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


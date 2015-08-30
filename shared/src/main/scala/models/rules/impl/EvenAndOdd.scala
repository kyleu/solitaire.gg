// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.game._
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Maximum cards for foundation (F0m): 7
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation rank match rule (F0r): 256 (Build up by 2)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 2 (2)
 *   Maximum cards for foundation (F1m): 6
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Reserve initial cards (R0d): 6
 *   Number of reserve piles (R0n): 3
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Left mouse interface function (leftfunc): 2
 *   Similar to (like): boulevard
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Number of decks (ndecks): 1 (1 deck)
 *   Touch interface function (touchfunc): 2
 */
object EvenAndOdd extends GameRules(
  id = "evenandodd",
  completed = true,
  title = "Even and Odd",
  like = Some("boulevard"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/even-and-odd.htm")),
  description = "A one-deck version of ^boulevard^.",
  layout = "sw.ff|rt",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 7
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 3,
      initialCards = 6,
      cardsFaceDown = -1
    )
  )
)

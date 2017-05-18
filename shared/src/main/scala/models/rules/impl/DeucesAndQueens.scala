package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Ace Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation low rank (F0b): 2 (2)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Initial card restriction (F0u): 0 (None)
 *   Foundation name (F1Nm): King Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 12 (Q)
 *   Can move cards from foundation (F1mb): 1 (Always)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Foundation Sets (Fn): 2
 *   Reserve initial cards (R0d): 13
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 3
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 4
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Similar to (like): acesandkings
 *   Low card (lowpip): -1 (.)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object DeucesAndQueens extends GameRules(
  id = "deucesandqueens",
  completed = true,
  title = "Deuces and Queens",
  like = Some("acesandkings"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/deuces_and_queens.htm")),
  layout = "swff|:::r:t",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
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
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Queen),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 3,
      initialCards = 13,
      cardsFaceDown = 0
    )
  )
)

// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Aces Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Foundation name (F1Nm): Sixes Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 6 (6)
 *   Maximum cards for foundation (F1m): 6
 *   Can move cards from foundation (F1mb): 1 (Always)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Foundation name (F2Nm): Sevens Foundation
 *   Auto-move cards to foundation (F2a): 0 (Never)
 *   Foundation low rank (F2b): 7 (7)
 *   Maximum cards for foundation (F2m): 7
 *   Can move cards from foundation (F2mb): 1 (Always)
 *   Number of foundation piles (F2n): 4 (4 stacks)
 *   Foundation suit match rule (F2s): 5 (Regardless of suit)
 *   Foundation name (F3Nm): Kings Foundation
 *   Auto-move cards to foundation (F3a): 0 (Never)
 *   Foundation low rank (F3b): 22 (Deck's high card)
 *   Can move cards from foundation (F3mb): 1 (Always)
 *   Number of foundation piles (F3n): 4 (4 stacks)
 *   Foundation rank match rule (F3r): 32 (Build down)
 *   Foundation suit match rule (F3s): 5 (Regardless of suit)
 *   Foundation Sets (Fn): 4
 *   Reserve initial cards (R0d): 13
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 3
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Similar to (like): acesandkings
 *   Number of decks (ndecks): 3 (3 decks)
 */
object RacingAces extends GameRules(
  id = "racingaces",
  completed = true,
  title = "Racing Aces",
  like = Some("acesandkings"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/racing_aces.htm")),
  description = "A three-deck version of ^acesandkings^ invented by Thomas Warfield.",
  layout = Some("sw|ff|ff|:t|::.r"),
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "Sixes Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Six),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down,
      maxCards = 6
    ),
    FoundationRules(
      name = "Sevens Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Seven),
      suitMatchRule = SuitMatchRule.Any,
      maxCards = 7
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 3,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
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

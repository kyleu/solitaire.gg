// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 8 (8 cards)
 *   *C0fx (C0fx): 1
 *   Number of cells (C0n): 8
 *   Can move cards to cell from (C0o): BIT_TABLEAU
 *   Foundation name (F0Nm): Ace Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): 0 (None)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   TODO (F0u): 2
 *   Foundation name (F1Nm): King Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): 0 (None)
 *   Can move cards from foundation (F1mb): 1 (Always)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 0x0020
 *   TODO (F1u): 2
 *   Foundation Sets (Fn): 2
 *   *RDc (RDc): 1
 *   *RDdo (RDdo): BIT_TABLEAU|BIT_STOCK
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   *T0an (T0an): 4
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 6
 *   May move to non-empty tableau from (T0o): BIT_TABLEAU|BIT_CELL
 *   Tableau rank match rule for building (T0r): 0x0000
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealchunk): 4 (Four at a time)
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): cicely
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): tournament
 *   Touch interface function (touchfunc): 0x2|0x20
 */
object Tournament extends GameRules(
  id = "tournament",
  title = "Tournament",
  like = Some("cicely"),
  description = "A two-deck game where no building is allowed on the tableau, and you must rely on eight cells to move your cards to the foundation" +
  ".",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(4)
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      mayMoveToNonEmptyFrom = Seq("Cell", "Tableau")
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 8,
      canMoveFrom = Seq("Tableau"),
      initialCards = 8
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      shuffleBeforeRedeal = false
    )
  ),
  complete = false
)


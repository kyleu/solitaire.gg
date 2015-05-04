// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 8 (8 cards)
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
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   May move to non-empty tableau from (T0o): BIT_TABLEAU|BIT_CELL
 *   Tableau rank match rule for building (T0r): 0x0080|0x0020
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealchunk): 4 (Four at a time)
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): caprice
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): tournament
 */
object Cicely extends GameRules(
  id = "cicely",
  title = "Cicely",
  like = Some("caprice"),
  related = Seq("tournament"),
  description = "A variation of ^tournament^ and ^kingsdowneights^ where you can build up and down on the tableau.",
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
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
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
  complete = false
)


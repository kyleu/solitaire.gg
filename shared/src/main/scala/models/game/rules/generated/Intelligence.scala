// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Deal order (RDd): 1|0|8
 *   *RDdo (RDdo): BIT_TABLEAU|BIT_STOCK
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   *RDpo (RDpo): BIT_TABLEAU|BIT_STOCK
 *   Shuffle before redealing (RDs): 1 (Yes)
 *   Enable stock (Sn): 1 (1 stock)
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   *T0an (T0an): 3
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau action during deal (T0dd): 4 (Move cards to empty foundations and replace)
 *   Custom initial cards (T0ds): UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU U
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 18
 *   Tableau rank match rule for building (T0r): 0x0080|0x0020
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Similar to (like): labellelucie
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Intelligence extends GameRules(
  id = "intelligence",
  title = "Intelligence",
  like = Some("labellelucie"),
  description = "A two-deck version of ^labellelucie^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      actionDuringDeal = PileAction.MoveToEmptyFoundationAndReplace
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      dealOrder = DealOrder.ColumnsLeftToRightTopToBottom
    )
  ),
  complete = false
)


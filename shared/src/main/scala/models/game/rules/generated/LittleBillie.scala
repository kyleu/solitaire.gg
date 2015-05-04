// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Cells name (C0Nm): Reserve
 *   Cell name (C0Ns): Reserve
 *   Card initially dealt into cells (C0d): 8 (8 cards)
 *   Number of cells (C0n): 8
 *   Foundation name (F0Nm): Aces Foundation
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   TODO (F0u): 2
 *   Foundation name (F1Nm): Kings Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 0x0020
 *   TODO (F1u): 2
 *   Foundation Sets (Fn): 2
 *   Deal order (RDd): 1|0|8
 *   Allowed pick ups/redeals (RDn): 2 (2)
 *   Shuffle before redealing (RDs): 1 (Yes)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 24
 *   Tableau rank match rule for building (T0r): 0x0000
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): buffalobill
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): littlebillie
 */
object LittleBillie extends GameRules(
  id = "littlebillie",
  title = "Little Billie",
  like = Some("buffalobill"),
  related = Seq("littlebillie"),
  description = "In this game dating back to around 1900, no building is allowed, but you have some cells that can be used to uncover the cards you" +
  " need. Two redeals are allowed.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 24,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      name = "Reserve",
      pluralName = "Reserve",
      numPiles = 8,
      initialCards = 8
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


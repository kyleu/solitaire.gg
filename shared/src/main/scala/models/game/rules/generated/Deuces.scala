// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): busyaces
 *   Low card (lowpip): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): jacksinthebox, threescompany, castoutnines
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Deuces extends GameRules(
  id = "deuces",
  title = "Deuces",
  like = Some("busyaces"),
  related = Seq("jacksinthebox", "threescompany", "castoutnines"),
  description = "A more difficult variation of ^busyaces^ with fewer tableau piles.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Two
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
      initialCards = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


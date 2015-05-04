// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 0
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 3 (In same color)
 *   Tableau suit match rule for moving stacks (T0ts): 3 (In same color)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Low card (lowpip): -2 (?)
 */
object AgnesSorel extends GameRules(
  id = "agnessorel",
  title = "Agnes Sorel",
  description = "A variation on ^klondike^ where cards are dealt directly onto the tableau as in ^spider^.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameColor,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameColor,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


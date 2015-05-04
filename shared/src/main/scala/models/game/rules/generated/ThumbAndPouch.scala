// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Tableau suit match rule for moving stacks (T0ts): 2 (In different suits)
 */
object ThumbAndPouch extends GameRules(
  id = "thumbandpouch",
  title = "Thumb and Pouch",
  description = "Like ^klondike^, but easier, because cards can be played on tableau cards of any different suit.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.DifferentSuits,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


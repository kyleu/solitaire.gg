// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

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
      canMoveFrom = FoundationCanMoveFrom.Never,
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


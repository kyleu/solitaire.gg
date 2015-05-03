// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Bobby extends GameRules(
  id = "bobby",
  title = "Bobby",
  description = "A variation of ^robert^ with a second foundation pile to make it easier, but not much easier.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 2,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  complete = false
)


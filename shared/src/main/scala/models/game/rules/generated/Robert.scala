// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Robert extends GameRules(
  id = "robert",
  title = "Robert",
  related = Seq("bobby"),
  description = "An nearly unwinnable game with no tableau.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
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


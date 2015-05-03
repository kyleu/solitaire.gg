// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Spike extends GameRules(
  id = "spike",
  title = "Spike",
  description = "^klondike^ with three waste piles.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


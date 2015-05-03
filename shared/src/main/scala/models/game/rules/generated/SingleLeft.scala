// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SingleLeft extends GameRules(
  id = "singleleft",
  title = "Single Left",
  description = "Thomas Warfield's one-deck version of ^movingleft^.",
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
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


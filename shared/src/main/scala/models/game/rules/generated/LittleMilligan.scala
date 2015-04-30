// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object LittleMilligan extends GameRules(
  id = "littlemilligan",
  title = "Little Milligan",
  description = "A hard-to-win one-deck version of ^missmilligan^.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
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
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


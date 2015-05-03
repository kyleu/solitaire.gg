// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Easthaven extends GameRules(
  id = "easthaven",
  title = "Easthaven",
  description = "A one-deck cross between ^spider^ and ^klondike^.",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(3),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


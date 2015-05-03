// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Westcliff extends GameRules(
  id = "westcliff",
  title = "Westcliff",
  description = "An very easy ^klondike^ variant where you have ten tableau piles.",
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
      numPiles = 10,
      initialCards = InitialCards.Count(3),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


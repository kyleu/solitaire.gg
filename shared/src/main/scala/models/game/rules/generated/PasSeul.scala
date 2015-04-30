// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object PasSeul extends GameRules(
  id = "passeul",
  title = "Pas Seul",
  description = "A ^klondike^ variant with a rectangular tableau, differing from ^blindalleys^ only in the number of passes through the deck allowe" +
  "d. The name refers to a dance sequence for one person.",
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
      numPiles = 6,
      initialCards = InitialCards.Count(3),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


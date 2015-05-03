// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BlindAlleys extends GameRules(
  id = "blindalleys",
  title = "Blind Alleys",
  related = Seq("passeul"),
  description = "A ^klondike^ variant with a square tableau, differing from ^passeul^ only in the number of passes through the deck allowed.",
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      numPiles = 6,
      initialCards = InitialCards.Count(3),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


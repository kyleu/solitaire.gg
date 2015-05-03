// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object GoldMine extends GameRules(
  id = "goldmine",
  title = "Gold Mine",
  description = "A ^klondike^ variation that starts with an empty tableau.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1),
      cardsDealt = StockCardsDealt.Count(3)
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
      initialCards = InitialCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Athena extends GameRules(
  id = "athena",
  title = "Athena",
  description = "A ^klondike^ variation with a rectangular starting tableau in which cards alternate face-up and face-down.",
  stock = Some(
    StockRules(
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
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Whitehorse extends GameRules(
  id = "whitehorse",
  title = "Whitehorse",
  like = Some("klondike"),
  description = "An easy ^klondike^ variant where instead of dealing a lot of cards to the tableau, we have spaces that autofill",
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
      initialCards = InitialCards.Count(1),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


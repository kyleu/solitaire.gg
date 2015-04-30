// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object KlondikeGalleryMode extends GameRules(
  id = "klondikegallery",
  title = "Klondike (Gallery Mode)",
  description = "The world's most famous solitaire game played in gallery mode so all stock cards are always visible and the playable ones are rais" +
  "ed",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(
    WasteRules(
      name = "Gallery"
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
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


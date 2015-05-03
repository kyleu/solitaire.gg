// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object JumboKlondike extends GameRules(
  id = "jumboklondike",
  title = "Jumbo Klondike",
  description = "^klondike^ played with a six-suit deck.",
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
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


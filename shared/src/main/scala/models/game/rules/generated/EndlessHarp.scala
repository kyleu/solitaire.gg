// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object EndlessHarp extends GameRules(
  id = "endlessharp",
  title = "Endless Harp",
  description = "A variation of Big Harp which allows unlimited redeals.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


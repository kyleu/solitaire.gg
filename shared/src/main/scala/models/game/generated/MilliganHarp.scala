// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object MilliganHarp extends GameRules(
  id = "milliganharp",
  title = "Milligan Harp",
  description = "A cross between ^missmilligan^ and the two-deck ^klondike^ variant known as Harp.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
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
      numPiles = 8,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


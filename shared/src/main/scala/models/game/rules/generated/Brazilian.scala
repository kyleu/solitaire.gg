// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Brazilian extends GameRules(
  id = "brazilian",
  title = "Brazilian",
  description = "In this two-deck ^klondike^ variant from Brazil you deal to the tableau instead of to a waste pile.",
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
      numPiles = 10,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


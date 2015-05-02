// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object AgnesBernauer extends GameRules(
  id = "agnesbernauer",
  title = "Agnes Bernauer",
  description = "A variation on ^klondike^ with seven reserves.",
  deckOptions = DeckOptions(
    lowRank = Some(Rank.Unknown)
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Reserve,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 7,
      initialCards = 1,
      cardsFaceDown = 100
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Elba extends GameRules(
  id = "elba",
  title = "Elba",
  like = Some("fortythieves"),
  description = "A variant of ^fortythieves^ with ^klondike^-like building rules.",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


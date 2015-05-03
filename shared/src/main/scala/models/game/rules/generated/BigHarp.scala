// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BigHarp extends GameRules(
  id = "bigharp",
  title = "Big Harp",
  like = Some("endlessharp"),
  description = "A two-deck ^klondike^ variation that is different than ^harp^ in several ways, without really being that much bigger.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
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
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


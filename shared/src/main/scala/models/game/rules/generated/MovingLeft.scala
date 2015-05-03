// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object MovingLeft extends GameRules(
  id = "movingleft",
  title = "Moving Left",
  related = Seq("singleleft", "tripleleft"),
  description = "A variant of ^gargantua^ or ^doubleklondike^ where empty spaces are automatically filled from the next column.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.NextPile,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


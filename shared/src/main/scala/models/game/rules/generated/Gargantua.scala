// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Gargantua extends GameRules(
  id = "gargantua",
  title = "Gargantua",
  description = "A two-deck version of ^klondike^ invented by Albert Morehead and Geoffrey Mott-Smith. You get two passes through the deck, dealing" +
  " cards one at a time.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      numPiles = 9,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


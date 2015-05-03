// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object DoubleGoldRush extends GameRules(
  id = "doublegoldrush",
  title = "Double Gold Rush",
  description = "A two-deck version of ^goldrush^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.FewerEachTime
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


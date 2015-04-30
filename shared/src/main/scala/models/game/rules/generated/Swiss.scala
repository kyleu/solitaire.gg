// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Swiss extends GameRules(
  id = "swiss",
  title = "Swiss",
  description = "Like ^klondike^ but aces are high and the tableau is pyramidical.",
  deckOptions = DeckOptions(
    lowRank = Some(Rank.Two)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DU",
        "DDU",
        "DDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


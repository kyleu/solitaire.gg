// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object AuntMary extends GameRules(
  id = "auntmary",
  title = "Aunt Mary",
  like = Some("thoughtful"),
  description = "A difficult ^klondike^ variation where the tableau contains one fewer pile but all cards are face up.",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Mystique extends GameRules(
  id = "mystique",
  title = "Mystique",
  like = Some("minerva"),
  description = "A variation of ^munger^ and ^minerva^ with a reserve sized half-way between the two.",
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
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 9,
      cardsFaceDown = 0
    )
  ),
  complete = false
)


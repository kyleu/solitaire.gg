// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object KlondikeTerritory extends GameRules(
  id = "klondiketerritory",
  title = "Klondike Territory",
  description = "A cross between ^flowergarden^ and ^klondike^, slightly more difficult than the similar Northwest Territory game.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


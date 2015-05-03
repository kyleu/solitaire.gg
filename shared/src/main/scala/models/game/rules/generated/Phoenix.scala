// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Phoenix extends GameRules(
  id = "phoenix",
  title = "Phoenix",
  description = "A more difficult variation of ^arizona^ where you build by alternate colors.",
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
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


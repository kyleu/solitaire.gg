// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Muse extends GameRules(
  id = "muse",
  title = "Muse",
  like = Some("kingalbert"),
  description = "This variation of ^kingalbert^ has cells instead of a reserve.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 7,
      initialCards = 7
    )
  ),
  complete = false
)


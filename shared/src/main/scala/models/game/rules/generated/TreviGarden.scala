// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TreviGarden extends GameRules(
  id = "trevigarden",
  title = "Trevi Garden",
  like = Some("stonewall"),
  description = "A variation of ^stonewall^ made easier by the addition of two cells.",
  waste = Some(
    WasteRules(
      name = "Fountain"
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
      name = "Garden",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2,
      initialCards = 2
    )
  ),
  complete = false
)


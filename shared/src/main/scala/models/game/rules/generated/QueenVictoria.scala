// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object QueenVictoria extends GameRules(
  id = "queenvictoria",
  title = "Queen Victoria",
  description = "This much easier variation of ^kingalbert^ allows stacks of cards to be moved.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


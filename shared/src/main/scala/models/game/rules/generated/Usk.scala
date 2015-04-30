// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Usk extends GameRules(
  id = "usk",
  title = "Usk",
  description = "A ^klondike^ variant without stock or waste. Unlike ^somerset^, moves of stacks are allowed and there is a redeal, but spaces can " +
  "only be filled by kings.",
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
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


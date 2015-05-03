// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Martha extends GameRules(
  id = "martha",
  title = "Martha",
  description = "An easy game with no stock where half the cards start face down. Somewhat similar to ^bakersdozen^.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered
    )
  ),
  complete = false
)


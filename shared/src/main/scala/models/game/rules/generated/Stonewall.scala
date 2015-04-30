// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Stonewall extends GameRules(
  id = "stonewall",
  title = "Stonewall",
  description = "Similar to ^flowergarden^, except some cards start face down, you must build in alternate colors, and you can move sequences.  A h" +
  "ard game to win.",
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
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


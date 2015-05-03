// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object AmericanCanister extends GameRules(
  id = "americancanister",
  title = "American Canister",
  description = "A difficult variation of ^canister^ with building by alternate colors.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


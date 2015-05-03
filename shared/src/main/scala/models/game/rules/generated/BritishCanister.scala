// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BritishCanister extends GameRules(
  id = "britishcanister",
  title = "British Canister",
  like = Some("canister"),
  description = "A difficult version of ^canister^ dating back to the 1890's. It resembles ^americancanister^ but does not allow stack moves and on" +
  "ly kings can fill spaces.",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Canister extends GameRules(
  id = "canister",
  title = "Canister",
  description = "Yet another generic solitaire game, with all cards dealt face up and no stock. This is quite easy when it's not impossible.",
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
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Wildflower extends GameRules(
  id = "wildflower",
  title = "Wildflower",
  description = "A variation of ^flowergarden^ in which you may move sequences of cards of the same suit together.",
  waste = Some(
    WasteRules(
      name = "Bouquet"
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
      name = "Flower Beds",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


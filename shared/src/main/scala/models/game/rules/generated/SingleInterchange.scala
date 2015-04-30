// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SingleInterchange extends GameRules(
  id = "singleinterchange",
  title = "Single Interchange",
  description = "A difficult one-deck variant of ^interchange^ invented by Thomas Warfield.",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
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
      numPiles = 5,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object LuckyPiles extends GameRules(
  id = "luckypiles",
  title = "Lucky Piles",
  description = "A vastly easier varition of ^luckythirteen^ in which you can build up or down.",
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
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


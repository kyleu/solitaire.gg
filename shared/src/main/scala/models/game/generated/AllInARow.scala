// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object AllInARow extends GameRules(
  id = "allinarow",
  title = "All in a Row",
  description = "A variation of ^golf^ without a stock. Most deals are winnable, but require a lot of advance planning to win.",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object YukonKings extends GameRules(
  id = "yukonkings",
  title = "Yukon Kings",
  description = "A difficult version of ^yukon^ without foundations.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)
// scalastyle:on


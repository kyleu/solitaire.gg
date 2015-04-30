// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Eliminator extends GameRules(
  id = "eliminator",
  title = "Eliminator",
  description = "An easy ^golf^-like game with six foundations.",
  foundations = Seq(
    FoundationRules(
      numPiles = 6,
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
      numPiles = 4,
      initialCards = InitialCards.Count(13),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object GoodMeasure extends GameRules(
  id = "goodmeasure",
  title = "Good Measure",
  description = "A much more difficult variation of ^bakersdozen^ with fewer tableau piles.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = InitialCards.Count(2),
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  ),
  complete = false
)
// scalastyle:on


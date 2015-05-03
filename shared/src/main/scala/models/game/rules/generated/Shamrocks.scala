// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Shamrocks extends GameRules(
  id = "shamrocks",
  title = "Shamrocks",
  description = "A variation of ^fan^ where you can build up or down regardless of suit, but are limited to three cards per stack.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 3,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  ),
  complete = false
)


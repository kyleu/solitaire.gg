// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object SimonSays extends GameRules(
  id = "simonsays",
  title = "Simon Says",
  description = "A blend of ^simplesimon^ with ^freecell^ invented by Thomas Warfield.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(

      numPiles = 2
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object WadingPool extends GameRules(
  id = "wadingpool",
  title = "Wading Pool",
  description = "An easier variation of ^wavemotion^ that permits building, but not stack moves, on the reserve.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 8,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


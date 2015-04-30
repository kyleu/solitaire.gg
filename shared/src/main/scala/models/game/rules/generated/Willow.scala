// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Willow extends GameRules(
  id = "willow",
  title = "Willow",
  description = "A ^klondike^ variation with four fan piles where we can build with cards of equal rank. Invented by Thomas Warfield.",
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = TableauFillEmptyWith.Aces
    ),
    TableauRules(
      name = "Fan",
      setNumber = 1,
      numPiles = 4,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


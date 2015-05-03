// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Isabel extends GameRules(
  id = "isabel",
  title = "Isabel",
  description = "Remove pairs of cards of equal rank from a 13x4 tableau.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


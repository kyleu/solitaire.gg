// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object PatientPairs extends GameRules(
  id = "patientpairs",
  title = "Patient Pairs",
  description = "As in ^simplepairs^, you remove pairs of cards of the same rank, but the cards start out all dealt to the tableau, so a smidgeon m" +
  "ore skill is involved.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      offscreen = true,
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


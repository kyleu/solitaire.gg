// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object GayGordons extends GameRules(
  id = "gaygordons",
  title = "Gay Gordons",
  description = "A pair removal game where you remove pairs that add to 11, Kings with Queens, or Jacks together.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQK,
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
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionAfterDeal = PileAction.LimitToTwoJacks
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 2,
      cardsFaceDown = 0
    )
  ),
  complete = false
)


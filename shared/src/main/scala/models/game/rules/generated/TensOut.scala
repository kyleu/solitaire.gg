// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TensOut extends GameRules(
  id = "tensout",
  title = "Tens Out",
  description = "A variation of ^fourteenout^ where we remove pairs adding to 10.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


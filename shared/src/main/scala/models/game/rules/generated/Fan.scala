// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Fan extends GameRules(
  id = "fan",
  title = "Fan",
  related = Seq("boxfan", "ceilingfan", "freefan", "midnightclover"),
  description = "The original Fan game involves building in suit on eighteen tableau piles.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


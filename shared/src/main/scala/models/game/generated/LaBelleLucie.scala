// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object LaBelleLucie extends GameRules(
  id = "labellelucie",
  title = "La Belle Lucie",
  description = "A classic solitaire where you build down in suit on the tableau and can redeal twice. Rarely winnable.",
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
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "UUU", "U"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


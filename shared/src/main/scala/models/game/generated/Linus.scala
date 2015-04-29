// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Linus extends GameRules(
  id = "linus",
  title = "Linus",
  description = "An easier variation of ^labellelucie^ where you build in alternate colors.",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)


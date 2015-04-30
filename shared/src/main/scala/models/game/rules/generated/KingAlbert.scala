// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object KingAlbert extends GameRules(
  id = "kingalbert",
  title = "King Albert",
  description = "This game, one of several games also known as \"Idiot's Delight,\" has a triangular tableau and seven reserve cards, all playable." +
  " It's usually unsolvable.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
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
      numPiles = 9,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object ThreeShufflesAndADraw extends GameRules(
  id = "threeshufflesandadraw",
  title = "Three Shuffles and a Draw",
  description = "A variation of ^labellelucie^ that adds a draw.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      dealOrder = DealOrder.ColumnsLeftToRightTopToBottom,
      drawsAllowed = 1,
      drawsAfterRedeals = true
    )
  ),
  complete = false
)


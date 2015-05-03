// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object RoyalFamily extends GameRules(
  id = "royalfamily",
  title = "Royal Family",
  like = Some("indefatigable"),
  description = "This game allows you to build up and down and fill spaces with any card, which makes the game so easy that you often don't need th" +
  "e redeal that you are allowed.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 1,
      shuffleBeforeRedeal = false,
      dealOrder = DealOrder.RowsLeftToRightTopToBottom
    )
  ),
  complete = false
)


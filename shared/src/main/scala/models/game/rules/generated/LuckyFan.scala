// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object LuckyFan extends GameRules(
  id = "luckyfan",
  title = "Lucky Fan",
  like = Some("freefan"),
  description = "A version of ^freefan^ in which no fan may hold more than three cards.",
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
      emptyFilledWith = TableauFillEmptyWith.Kings,
      maxCards = 3
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  ),
  complete = false
)


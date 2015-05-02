// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Stalactites extends GameRules(
  id = "stalactites",
  title = "Stalactites",
  description = "This suitless game with no building requires you to clear the tableau with only two cells you help you.",
  deckOptions = DeckOptions(
    lowRank = None
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = InitialCards.PileIndex,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object DoubletCell extends GameRules(
  id = "doubletcell",
  title = "Doublet Cell",
  description = "A combination between ^doublets^ and ^freecell^.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRankAndColor,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      wrapFromKingToAce = true,
      maxCards = 0,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules()),
  complete = false
)


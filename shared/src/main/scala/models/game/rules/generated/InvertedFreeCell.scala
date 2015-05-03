// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object InvertedFreeCell extends GameRules(
  id = "invertedfreecell",
  title = "Inverted FreeCell",
  like = Some("freecell"),
  description = "Just like ^freecell^ but we limber up our brains by building everything in the opposite direction.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      rankMatchRuleForBuilding = RankMatchRule.Up,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)


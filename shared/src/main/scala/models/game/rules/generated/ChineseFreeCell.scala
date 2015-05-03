// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object ChineseFreeCell extends GameRules(
  id = "chinesefreecell",
  title = "Chinese FreeCell",
  description = "A version of ^freecell^ played with only three suits.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  cells = Some(CellRules()),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Repair extends GameRules(
  id = "repair",
  title = "Repair",
  description = "A two-deck version of ^freecell^.",
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
      numPiles = 10,
      initialCards = InitialCards.Count(10),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      initialCards = 4
    )
  ),
  complete = false
)


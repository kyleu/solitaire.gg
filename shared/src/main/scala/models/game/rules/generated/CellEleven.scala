// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CellEleven extends GameRules(
  id = "celleleven",
  title = "Cell Eleven",
  like = Some("triplefreecell"),
  description = "A three-deck version of ^freecell^.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 11,
      initialCards = 2
    )
  ),
  complete = false
)


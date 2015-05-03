// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object DoubleYukon extends GameRules(
  id = "doubleyukon",
  title = "Double Yukon",
  description = "A two-deck variation of ^yukon^",
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
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUU",
        "DUUUUUU",
        "DDUUUUUU",
        "DDDUUUUUU",
        "DDDDUUUUUU",
        "DDDDDUUUUUU",
        "DDDDDDUUUUUU",
        "DDDDDDDUUUUUU",
        "DDDDDDDDUUUUUU",
        "DDDDDDDDDUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object TripleYukon extends GameRules(
  id = "tripleyukon",
  title = "Triple Yukon",
  description = "A three-deck variation of ^yukon^",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("UUUUUU", "DUUUUUU", "DDUUUUUU", "DDDUUUUUU", "DDDDUUUUUU", "DDDDDUUUUUU", "DDDDDDUUUUUU", "DDDDDDDUUUUUU", "DDDDDDDDUUUUUU", "DDDDDDDDDUUUUUU", "DDDDDDDDDDUUUUUU", "DDDDDDDDDDDUUUUUU", "DDDDDDDDDDDDUUUUUU"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object YukonOneSuit extends GameRules(
  id = "yukononesuit",
  title = "Yukon One Suit",
  description = "A one-suit variation of ^yukon^. The game is almost always winnable, but still makes you think a bit.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


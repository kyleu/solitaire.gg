// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object TripleScorpion extends GameRules(
  id = "triplescorpion",
  title = "Triple Scorpion",
  description = "A three-deck variation of ^scorpion^ with no reserve.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "DDDDDUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


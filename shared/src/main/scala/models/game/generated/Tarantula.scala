// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Tarantula extends GameRules(
  id = "tarantula",
  title = "Tarantula",
  description = "An easier variation of ^spider^ where you are allowed to move sequences that are all one color even if they aren't all of one suit" +
  ".",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameColor,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


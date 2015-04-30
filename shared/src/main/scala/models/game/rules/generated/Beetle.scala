// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Beetle extends GameRules(
  id = "beetle",
  title = "Beetle",
  description = "A variation of ^spider^ where all cards are dealt face up.",
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
      customInitialCards = Seq(
        "UUUUUU",
        "UUUUUU",
        "UUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


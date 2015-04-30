// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object MondoSpider extends GameRules(
  id = "mondospider",
  title = "Mondo Spider",
  description = "A rather arduous double-size eight-suit ^spider^ variant.",
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
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object ChineseKlondike extends GameRules(
  id = "chineseklondike",
  title = "Chinese Klondike",
  description = "A three-suit version of ^klondike^.",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
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
      numPiles = 12,
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


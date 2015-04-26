// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object BrownRecluse extends GameRules(
  id = "brownrecluse",
  title = "Brown Recluse",
  description = "This ^spider^ variant by Thomas Warfield has a stock and a waste pile. The tableau starts with just one card in each column, but s" +
  "paces are autofilled from the waste or stock.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


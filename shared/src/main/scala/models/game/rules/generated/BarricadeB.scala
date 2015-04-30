// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object BarricadeB extends GameRules(
  id = "barricadeb",
  title = "Barricade B",
  description = "An easier, but still very difficult, modification of ^block^ invented by Richard Mechen and Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.Count(2),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


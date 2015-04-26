// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object AliBaba extends GameRules(
  id = "alibaba",
  title = "Ali Baba",
  description = "A one-deck variation of ^fortyandeight^ where you can move sequences of cards together instead of just one at a time. With 40 card" +
  "s in the tableau, you only have 12 cards in your deck which makes for a lot of unsolvable games. But with a bit of luck you can op" +
  "en an empty space in your tableau and then things are likely to go smoothly.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


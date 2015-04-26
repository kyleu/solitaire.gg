// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object Kingsley extends GameRules(
  id = "kingsley",
  title = "Kingsley",
  description = "Reverse ^klondike^ which in theory is no more difficult, but which proves hard to wrap your head around if you are used to playing" +
  " it the other way round.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      rankMatchRuleForBuilding = RankMatchRule.Up,
      rankMatchRuleForMovingStacks = RankMatchRule.Up,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object MilliganYukon extends GameRules(
  id = "milliganyukon",
  title = "Milligan Yukon",
  like = Some("milliganharp"),
  description = "A cross between ^milliganharp^ and ^yukon^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Sevastopol extends GameRules(
  id = "sevastopol",
  title = "Sevastopol",
  description = "An easier version of ^kiev^ where four tableau piles start with three cards instead of four.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDU",
        "DDDU",
        "DDU",
        "DDDU",
        "DDU",
        "DDDU",
        "DDU"
      ),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


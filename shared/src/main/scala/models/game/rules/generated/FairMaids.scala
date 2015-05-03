// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object FairMaids extends GameRules(
  id = "fairmaids",
  title = "Fair Maids",
  description = "A variation of ^willothewisp^ where we build in alternate colors.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauNonEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


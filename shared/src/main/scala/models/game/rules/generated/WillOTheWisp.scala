// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object WillOTheWisp extends GameRules(
  id = "willothewisp",
  title = "Will o the Wisp",
  related = Seq("fairmaids"),
  description = "A one-deck version of ^spider^, with a rectangular 7x3 tableau.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(3),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


// Generated rules for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

object ScorpionTail extends GameRules(
  id = "scorpiontail",
  title = "Scorpion Tail",
  description = "A variation of ^scorpion^ where we build down by alternate color instead of down in suit.",
  stock = Some(
    StockRules(
      name = "Reserve",
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq("DDDUUUU", "DDDUUUU", "DDDUUUU", "DDDUUUU", "UUUUUUU", "UUUUUUU", "UUUUUUU"),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)


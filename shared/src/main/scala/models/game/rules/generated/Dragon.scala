// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Dragon extends GameRules(
  id = "dragon",
  title = "Dragon",
  description = "A variation of ^chinese^ where you build in the same suit.",
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
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "DUUUUUUU",
        "DDUUUUUU",
        "DDDUUUU",
        "DDDDUUU",
        "DDDDDUU",
        "DDDDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)


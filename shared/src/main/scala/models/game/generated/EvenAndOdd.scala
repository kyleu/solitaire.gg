// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object EvenAndOdd extends GameRules(
  id = "evenandodd",
  title = "Even and Odd",
  description = "A one-deck version of ^boulevard^.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(
    WasteRules(

    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 7,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 3,
      initialCards = 6,
      cardsFaceDown = 100
    )
  ),
  complete = false
)
// scalastyle:on


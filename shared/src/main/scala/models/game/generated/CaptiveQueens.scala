// Generated 2015-04-26 for Scalataire.
package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object CaptiveQueens extends GameRules(
  id = "captivequeens",
  title = "Captive Queens",
  description = "An easy and brainless variation of ^sixesandsevens^ also known as \"Quadrille\". Automoves default mostly off to give you something to do.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(
    WasteRules(

    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Fives Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      name = "Sixes Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      name = "Queens Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 1,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  complete = false
)
// scalastyle:on


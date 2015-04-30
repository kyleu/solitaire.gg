// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CaptiveQueens extends GameRules(
  id = "captivequeens",
  title = "Captive Queens",
  description = "An easy and brainless variation of ^sixesandsevens^ also known as \"Quadrille\". Automoves default mostly off to give you somethin" +
  "g to do.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
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
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never
    ),
    FoundationRules(
      name = "Queens Foundation",
      setNumber = 2,
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


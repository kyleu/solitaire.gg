// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Contradance extends GameRules(
  id = "contradance",
  title = "Contradance",
  description = "A variation of ^sixesandsevens^ that is just as brainless as ^captivequeens^, but requires vastly more luck to ever win.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      maxCards = 6,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 7,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  complete = false
)


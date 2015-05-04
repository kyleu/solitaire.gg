// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): "Fives Foundation"
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation low rank (F0b): 5 (5)
 *   Maximum cards for foundation (F0m): 6
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation rank match rule (F0r): 0x0020
 *   Foundation name (F1Nm): "Sixes Foundation"
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 6 (6)
 *   Maximum cards for foundation (F1m): 6
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation name (F2Nm): "Queens Foundation"
 *   Foundation low rank (F2b): 12 (Q)
 *   Maximum cards for foundation (F2m): 1
 *   Number of foundation piles (F2n): 4 (4 stacks)
 *   Foundation Sets (Fn): 3
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Maximum deals from stock (maxdeals): 3 (3)
 */
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
      maxCards = 6
    ),
    FoundationRules(
      name = "Sixes Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 6
    ),
    FoundationRules(
      name = "Queens Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      wrapFromKingToAce = true,
      maxCards = 1,
      autoMoveCards = true
    )
  ),
  complete = false
)


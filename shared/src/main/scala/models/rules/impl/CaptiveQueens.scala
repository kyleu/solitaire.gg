package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): "Fives Foundation"
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation low rank (F0b): 5 (5)
 *   Maximum cards for foundation (F0m): 6
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation rank match rule (F0r): 32 (Build down)
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
  completed = true,
  title = "Captive Queens",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/captive_queens.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/captive_queens.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Captive_Queens"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/captive_queens.html"),
    Link("An analysis of Captive Queens", "www.somethinkodd.com/oddthinking/2013/05/30/analysis-of-captive-queens/"),
    Link("Jan Wolter's Experiments", "/article/captivequeens.html")
  ),
  layout = ":sw|f|f|f",
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
      lowRank = FoundationLowRank.SpecificRank(Rank.Five),
      rankMatchRule = RankMatchRule.Down,
      maxCards = 6
    ),
    FoundationRules(
      name = "Sixes Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Six),
      maxCards = 6
    ),
    FoundationRules(
      name = "Queens Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Queen),
      maxCards = 1,
      autoMoveCards = true
    )
  )
)

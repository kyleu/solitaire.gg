package models.rules.impl

import models.card.Rank
import models.rules._

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
  stock = Some(StockRules(maximumDeals = Some(3))),
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

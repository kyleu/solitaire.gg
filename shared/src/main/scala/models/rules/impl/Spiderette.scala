package models.rules.impl

import models.rules._

object Spiderette extends GameRules(
  id = "spiderette",
  completed = false,
  title = "Spiderette",
  like = Some("spider"),
  related = Seq("spidike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spiderette.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/spiderette.html"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Spiderette.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/spiderette.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spiderette.html.en"),
    Link("Solitaire City", "www.solitairecity.com/iPhone/Spiderette.shtml")
  ),
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)

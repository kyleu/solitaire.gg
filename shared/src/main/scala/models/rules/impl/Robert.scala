package models.rules.impl

import models.rules._

object Robert extends GameRules(
  id = "robert",
  completed = false,
  title = "Robert",
  related = Seq("bobby"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/robert.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/robert.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/robert.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/robert.htm")
  ),
  layout = "swf",
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  )
)

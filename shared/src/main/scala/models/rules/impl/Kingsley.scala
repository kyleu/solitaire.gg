package models.rules.impl

import models.rules._

object Kingsley extends GameRules(
  id = "kingsley",
  completed = true,
  title = "Kingsley",
  like = Some("klondike"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingsley.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/kingsley.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Kingsley.htm")
  ),
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      rankMatchRuleForBuilding = RankMatchRule.Up,
      rankMatchRuleForMovingStacks = RankMatchRule.Up,
      emptyFilledWith = FillEmptyWith.LowRank
    )
  )
)

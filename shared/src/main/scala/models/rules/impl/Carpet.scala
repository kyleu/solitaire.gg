package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Carpet extends GameRules(
  id = "carpet",
  completed = true,
  title = "Carpet",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Carpet_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/carpet.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/carpet.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/carpet.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Carpet.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/carpet.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/carpet.htm")
  ),
  layout = "swf|2t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 20,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Waste)
    )
  )
)

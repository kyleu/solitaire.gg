package models.rules.impl

import models.rules._

object Harp extends GameRules(
  id = "harp",
  completed = true,
  title = "Harp",
  related = Seq("tripleharp"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/harp.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/harp.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/harp.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Harp.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/harp.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/harp.html")
  ),
  layout = "swf|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(4)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)

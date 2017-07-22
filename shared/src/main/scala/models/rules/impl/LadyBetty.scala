package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object LadyBetty extends GameRules(
  id = "ladybetty",
  completed = false,
  title = "Lady Betty",
  like = Some("sirtommy"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lady_betty.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/lady_betty.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lady_betty.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/lady_betty.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/lady-betty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LadyBetty.htm")
  ),
  layout = "sf|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Reserve",
      numPiles = 6,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Stock),
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Stock)
    )
  )
)

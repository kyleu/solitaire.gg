package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object CornerSuite extends GameRules(
  id = "cornersuite",
  completed = true,
  title = "Corner Suite",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/corner_suite.htm")),
  layout = "sw::f|t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  )
)

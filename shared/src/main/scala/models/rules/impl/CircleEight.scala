package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object CircleEight extends GameRules(
  id = "circleeight",
  completed = false,
  title = "Circle Eight",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/circle_eight.htm")),
  layout = "sw|t",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Waste)
    )
  )
)

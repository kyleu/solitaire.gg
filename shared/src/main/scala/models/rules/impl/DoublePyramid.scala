package models.rules.impl

import models.rules._

object DoublePyramid extends GameRules(
  id = "doublepyramid",
  completed = true,
  title = "Double Pyramid",
  like = Some("pyramid"),
  related = Seq("anubis"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_pyramid.htm")),
  layout = "p|:::.swf",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(canMoveFrom = FoundationCanMoveFrom.Never, visible = false, autoMoveCards = true)),
  pyramids = Seq(PyramidRules(
    height = 9,
    rankMatchRuleForBuilding = RankMatchRule.Down,
    rankMatchRuleForMovingStacks = RankMatchRule.Down,
    mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
    mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
  ))
)

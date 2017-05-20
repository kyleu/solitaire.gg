package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object KingTut extends GameRules(
  id = "kingtut",
  completed = false,
  title = "King Tut",
  like = Some("pyramid"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/king_tut.htm"),
    Link("Michael Keller's Discussion", "www.solitairelaboratory.com/pyramid.html")
  ),
  layout = "swf|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)
